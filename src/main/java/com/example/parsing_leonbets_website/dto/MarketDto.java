package com.example.parsing_leonbets_website.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketDto {

  private String name;
  private List<RunnerDto> runners;
}
