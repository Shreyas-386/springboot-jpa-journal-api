package com.pict.journalApp.service;

import com.pict.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Value("${api.weather.key}")
    private String apiKey;

    private static final String API = "http://api.weatherapi.com/v1/current.json?key=VAL&q=London&aqi=no";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getTemp() {
        String finalAPI = API.replace("VAL", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
