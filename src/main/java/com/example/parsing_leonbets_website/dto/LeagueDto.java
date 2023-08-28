package com.example.parsing_leonbets_website.dto;

import com.example.parsing_leonbets_website.enums.SportType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueDto {

  private String id;
  private String name;
  private SportType family;
  private int topOrder;
  private boolean top;
}
