package com.example.parsing_leonbets_website.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchDetailsDto {

  private String id;
  private String name;
  private long kickoff;
  private List<MarketDto> markets;
}
