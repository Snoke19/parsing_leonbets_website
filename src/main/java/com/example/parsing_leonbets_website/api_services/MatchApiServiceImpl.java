package com.example.parsing_leonbets_website.api_services;

import com.example.parsing_leonbets_website.dto.AllMatchDto;
import com.example.parsing_leonbets_website.dto.MatchDetailsDto;
import com.example.parsing_leonbets_website.dto.MatchDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MatchApiServiceImpl implements MatchApiService {

  private static final String BASE_URL = "/changes/all?ctag=en-US&vtag=9c2cd386-31e1-4ce9-a140-28e9b63a9300";
  private static final String EVENT_URL = "/event/all?ctag=en-US&eventId=";

  private final RestTemplate restTemplate;

  public MatchApiServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public MatchDto fetchFirstMatchFromTopLeague(String leagueId) {
    String url = BASE_URL + "&league_id=" + leagueId;
    AllMatchDto allMatchDto = restTemplate.getForObject(url, AllMatchDto.class);

    return allMatchDto != null && !allMatchDto.getData().isEmpty()
      ? allMatchDto.getData().get(0)
      : new MatchDto();
  }

  @Override
  public MatchDetailsDto fetchMatchDetailsById(String matchId) {
    String url = EVENT_URL + matchId;
    MatchDetailsDto matchDetailsDto = restTemplate.getForObject(url, MatchDetailsDto.class);

    return matchDetailsDto != null ? matchDetailsDto : new MatchDetailsDto();
  }
}