package com.example.parsing_leonbets_website.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegionDto {

  private String id;
  private String name;
  private String family;
  private List<LeagueDto> leagues;
}
