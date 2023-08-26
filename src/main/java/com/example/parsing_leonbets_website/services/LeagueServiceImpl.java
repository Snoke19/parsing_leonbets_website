package com.example.parsing_leonbets_website.services;

import com.example.parsing_leonbets_website.api_services.SportsApiService;
import com.example.parsing_leonbets_website.dto.LeagueDto;
import com.example.parsing_leonbets_website.dto.RegionDto;
import com.example.parsing_leonbets_website.dto.SportDto;
import com.example.parsing_leonbets_website.enums.SportType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {

  private final SportsApiService sportsApiService;

  public LeagueServiceImpl(SportsApiService sportsApiService) {
    this.sportsApiService = sportsApiService;
  }

  @Override
  public List<LeagueDto> fetchTopLeaguesBySportTypes(EnumSet<SportType> sportsTypes) {

    List<SportDto> filteredSports = sportsApiService.fetchAllSports().stream()
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
}
