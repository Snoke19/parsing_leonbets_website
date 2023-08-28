package com.example.parsing_leonbets_website;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientTemplate {

  private static final String LEON_BETS = "https://leonbets.com/api-2/betline";

  public <T> T getResponse(String uri, Class<T> pojoClass) {
    HttpClient httpClient = HttpClient.newBuilder()
      .connectTimeout(Duration.ofSeconds(5))
      .followRedirects(HttpClient.Redirect.NORMAL)
      .build();

    // Create a request
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(LEON_BETS + uri))
      .timeout(Duration.ofSeconds(15))
      .build();

    try {

      HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() != 200) {
        System.out.println("Request failed with status code: " + response.statusCode());
      }

      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(response.body(), pojoClass);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

    return null;
  }
}
