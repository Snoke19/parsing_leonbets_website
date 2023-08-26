package com.example.parsing_leonbets_website;

import com.example.parsing_leonbets_website.dto.LeagueDto;
import com.example.parsing_leonbets_website.enums.SportType;
import com.example.parsing_leonbets_website.final_info.AllInfoMatch;
import com.example.parsing_leonbets_website.services.LeagueService;
import com.example.parsing_leonbets_website.services.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ParsingLeonBetsWebsiteApplicationTests {

  @Autowired
  private LeagueService leagueService;

  @Autowired
  private MatchService matchService;

  @Test
  void fetchAndPrintMatchesFromTopLeaguesInfo() {

    List<LeagueDto> leagues = leagueService.fetchTopLeaguesBySportTypes(SportType.getAllTypes());
    List<AllInfoMatch> allInfoMatches = new ArrayList<>();

    leagues.forEach(league -> {
      AllInfoMatch allInfoMatch = AllInfoMatch.builder()
        .family(league.getFamily())
        .league(league.getName())
        .match(matchService.fetchMatchByLeague(league))
        .build();

      allInfoMatches.add(allInfoMatch);
    });

    ObjectMapper objectMapper = new ObjectMapper();
    for (AllInfoMatch match : allInfoMatches) {
      try {
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(match));
      } catch (JsonProcessingException e) {
        System.out.println("convert object to json exception: " + e.getMessage());
      }
    }
  }

  @ParameterizedTest
  @EnumSource(value = SportType.class, names = {"FOOTBALL", "TENNIS", "ICE_HOCKEY", "BASKETBALL"})
  void testReceivingAllLeaguesByAllSports(SportType sportType) {

    List<LeagueDto> sportDtoList = leagueService.fetchTopLeaguesBySportTypes(EnumSet.of(sportType));
    for (LeagueDto leagueDto : sportDtoList) {
      assertEquals(sportType, leagueDto.getFamily());
      assertTrue(leagueDto.isTop());
    }
  }

  @Test
  void testCannotReceiveAllLeaguesWithoutSport() {
    List<LeagueDto> sportDtoList = leagueService.fetchTopLeaguesBySportTypes(EnumSet.noneOf(SportType.class));
    assertEquals(0, sportDtoList.size());
  }
}
