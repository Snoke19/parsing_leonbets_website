package com.example.parsing_leonbets_website.api_services;

import com.example.parsing_leonbets_website.dto.SportDto;

import java.util.List;

public interface SportsApiService {

  List<SportDto> fetchAllSports();
}
