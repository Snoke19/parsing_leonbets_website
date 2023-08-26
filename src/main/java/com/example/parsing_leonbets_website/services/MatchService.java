package com.example.parsing_leonbets_website.services;

import com.example.parsing_leonbets_website.dto.LeagueDto;
import com.example.parsing_leonbets_website.final_info.Match;

public interface MatchService {

  Match fetchMatchByLeague(LeagueDto league);
}
