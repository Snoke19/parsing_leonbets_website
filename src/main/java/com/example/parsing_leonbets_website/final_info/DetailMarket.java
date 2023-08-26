package com.example.parsing_leonbets_website.final_info;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailMarket {

  private String name;
  private String coefficient;
  private String id;
}
