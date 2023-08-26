package com.example.parsing_leonbets_website.final_info;

import com.example.parsing_leonbets_website.enums.SportType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AllInfoMatch {

  private SportType family;
  private String league;
  private Match match;
}
