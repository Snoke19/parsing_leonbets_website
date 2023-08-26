package com.example.parsing_leonbets_website.final_info;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Market {

  private String name;
  private List<DetailMarket> details;
}
