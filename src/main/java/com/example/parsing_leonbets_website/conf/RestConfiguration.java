package com.example.parsing_leonbets_website.conf;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplateBuilder().rootUri("https://leonbets.com/api-2/betline").build();
  }
}
