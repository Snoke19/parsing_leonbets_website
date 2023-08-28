package com.example.parsing_leonbets_website.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetailsDto {

  private String id;
  private String name;
  private long kickoff;
  private List<MarketDto> markets;
}
