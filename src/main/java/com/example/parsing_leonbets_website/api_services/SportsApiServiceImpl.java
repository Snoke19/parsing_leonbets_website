package com.example.parsing_leonbets_website.api_services;

import com.example.parsing_leonbets_website.dto.SportDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class SportsApiServiceImpl implements SportsApiService {

  private static final String SPORTS_URL = "/sports?ctag=en-US";

  private final RestTemplate restTemplate;

  public SportsApiServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<SportDto> fetchAllSports() {
    SportDto[] sports = restTemplate.getForObject(SPORTS_URL, SportDto[].class);
    return sports != null ? List.of(sports) : Collections.emptyList();
  }
}
