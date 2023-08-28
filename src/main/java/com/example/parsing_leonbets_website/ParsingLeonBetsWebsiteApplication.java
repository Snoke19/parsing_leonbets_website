package com.example.parsing_leonbets_website;


import com.example.parsing_leonbets_website.dto.*;
import com.example.parsing_leonbets_website.enums.SportType;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class ParsingLeonBetsWebsiteApplication {

  private static final String SPORTS_URL = "/sports?ctag=en-US";
  private static final String EVENT_URL = "/event/all?ctag=en-US&eventId=";
  private static final String BASE_URL = "/changes/all?ctag=en-US&vtag=9c2cd386-31e1-4ce9-a140-28e9b63a9300";

  private static final HttpClientTemplate httpClientTemplate = new HttpClientTemplate();

  public static void main(String[] args) {
    List<LeagueDto> leagues = fetchTopLeaguesBySportTypes(SportType.getAllTypes());

    leagues.forEach(league -> {
      System.out.println(league.getFamily() + ", " + league.getName());

      MatchDetailsDto matchDetailsDto = fetchMatchByLeague(league);

      System.out.println("\t\t" + matchDetailsDto.getName() + ", " + convertTimestampToDate(matchDetailsDto) + ", " + matchDetailsDto.getId());

      for (MarketDto market : matchDetailsDto.getMarkets()) {

        System.out.println("\t\t\t" + market.getName());

        for (RunnerDto detail : market.getRunners()) {
          System.out.println("\t\t\t\t" + detail.getName() + ", " + detail.getPriceStr() + ", " + detail.getId());
        }
      }
    });
  }

  public static List<LeagueDto> fetchTopLeaguesBySportTypes(EnumSet<SportType> sportsTypes) {

    List<SportDto> filteredSports = fetchAllSports().stream()
      .filter(sportDto -> sportsTypes.contains(sportDto.getFamily()))
      .toList();

    List<LeagueDto> topLeagues = new ArrayList<>();

    for (SportDto sport : filteredSports) {
      for (RegionDto region : sport.getRegions()) {
        List<LeagueDto> leagues = region.getLeagues().stream()
          .filter(LeagueDto::isTop)
          .sorted(Comparator.comparingInt(LeagueDto::getTopOrder))
          .peek(league -> {
            league.setFamily(sport.getFamily());
            league.setName(region.getName() + " - " + league.getName());
          }).toList();
        topLeagues.addAll(leagues);
      }
    }

    return topLeagues;
  }

  private static MatchDetailsDto fetchMatchByLeague(LeagueDto league) {
    MatchDto firstMatchFromTopLeague = fetchFirstMatchFromTopLeague(league.getId());
    return fetchMatchDetailsById(firstMatchFromTopLeague.getId());
  }

  private static LocalDateTime convertTimestampToDate(MatchDetailsDto matchDto) {
    Instant instant = Instant.ofEpochMilli(matchDto.getKickoff());

    return instant.atOffset(ZoneOffset.UTC).toLocalDateTime();
  }

  private static MatchDto fetchFirstMatchFromTopLeague(String leagueId) {
    String url = BASE_URL + "&league_id=" + leagueId;
    AllMatchDto allMatchDto = httpClientTemplate.getResponse(url, AllMatchDto.class);

    return allMatchDto != null && !allMatchDto.getData().isEmpty()
      ? allMatchDto.getData().get(0)
      : new MatchDto();
  }

  public static MatchDetailsDto fetchMatchDetailsById(String matchId) {
    String url = EVENT_URL + matchId;
    MatchDetailsDto matchDetailsDto = httpClientTemplate.getResponse(url, MatchDetailsDto.class);

    return matchDetailsDto != null ? matchDetailsDto : new MatchDetailsDto();
  }

  public static List<SportDto> fetchAllSports() {
    SportDto[] sports = httpClientTemplate.getResponse(SPORTS_URL, SportDto[].class);
    return sports != null ? List.of(sports) : Collections.emptyList();
  }
}
