package com.example.parsing_leonbets_website.services;

import com.example.parsing_leonbets_website.dto.LeagueDto;
import com.example.parsing_leonbets_website.enums.SportType;

import java.util.EnumSet;
import java.util.List;

public interface LeagueService {

  List<LeagueDto> fetchTopLeaguesBySportTypes(EnumSet<SportType> sportTypes);
}
