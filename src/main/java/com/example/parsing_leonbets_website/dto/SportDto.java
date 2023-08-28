package com.example.parsing_leonbets_website.dto;


import com.example.parsing_leonbets_website.enums.SportType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SportDto {

  private SportType family;
  private List<RegionDto> regions;
}
