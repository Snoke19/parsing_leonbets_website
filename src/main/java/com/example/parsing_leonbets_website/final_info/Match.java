package com.example.parsing_leonbets_website.final_info;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Match {

  private String name;
  private String kickOff;
  private String id;
  private List<Market> markets;
}
