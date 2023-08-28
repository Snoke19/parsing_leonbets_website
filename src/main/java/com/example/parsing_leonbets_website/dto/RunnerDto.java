package com.example.parsing_leonbets_website.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RunnerDto {

  private String name;
  private String priceStr;
  private String id;
}
