package com.example.parsing_leonbets_website.dto;


import com.example.parsing_leonbets_website.enums.SportType;
import lombok.Data;

import java.util.List;

@Data
public class SportDto {

  private SportType family;
  private List<RegionDto> regions;
}
