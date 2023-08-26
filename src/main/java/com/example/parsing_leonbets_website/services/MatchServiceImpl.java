package com.example.parsing_leonbets_website.services;

import com.example.parsing_leonbets_website.api_services.MatchApiService;
import com.example.parsing_leonbets_website.dto.LeagueDto;
import com.example.parsing_leonbets_website.dto.MarketDto;
import com.example.parsing_leonbets_website.dto.MatchDetailsDto;
import com.example.parsing_leonbets_website.dto.MatchDto;
import com.example.parsing_leonbets_website.final_info.DetailMarket;
import com.example.parsing_leonbets_website.final_info.Market;
import com.example.parsing_leonbets_website.final_info.Match;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

  private final MatchApiService matchApiService;

  public MatchServiceImpl(MatchApiService matchApiService) {
    this.matchApiService = matchApiService;
  }

  @Override
  public Match fetchMatchByLeague(LeagueDto league) {
    MatchDto firstMatchFromTopLeague = matchApiService.fetchFirstMatchFromTopLeague(league.getId());
    MatchDetailsDto matchDetailsDto = this.matchApiService.fetchMatchDetailsById(firstMatchFromTopLeague.getId());

    List<Market> markets = matchDetailsDto.getMarkets().stream()
      .map(this::createMarket)
      .collect(Collectors.toList());

    Instant instant = Instant.ofEpochMilli(matchDetailsDto.getKickoff());
    LocalDateTime kickOff = instant.atOffset(ZoneOffset.UTC).toLocalDateTime();

    return Match.builder()
      .name(matchDetailsDto.getName())
      .kickOff(kickOff.toString())
      .id(matchDetailsDto.getId())
      .markets(markets)
      .build();
  }

  private Market createMarket(MarketDto marketDto) {
    return Market.builder()
      .name(marketDto.getName())
      .details(marketDto.getRunners().stream()
        .map(detailMarket -> DetailMarket.builder()
          .name(detailMarket.getName())
          .coefficient(detailMarket.getPriceStr())
          .id(detailMarket.getId())
          .build())
        .collect(Collectors.toList()))
      .build();
  }
}
