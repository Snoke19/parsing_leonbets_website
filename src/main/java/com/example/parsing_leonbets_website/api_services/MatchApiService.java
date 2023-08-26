package com.example.parsing_leonbets_website.api_services;

import com.example.parsing_leonbets_website.dto.MatchDetailsDto;
import com.example.parsing_leonbets_website.dto.MatchDto;

public interface MatchApiService {

  MatchDto fetchFirstMatchFromTopLeague(String leagueId);
  MatchDetailsDto fetchMatchDetailsById(String matchId);
}
