package com.pict.journalApp.service;

import com.pict.journalApp.api.response.QuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QuoteService {
    @Value("${api.quote.key}")
    private String apikey;

    private static final String API = "https://api.api-ninjas.com/v2/randomquotes?X-Api-Key=API&category=happiness";

    @Autowired
    private RestTemplate restTemplate;

    public QuoteResponse[] getQuote() {
        String finalAPI = API.replace("API", apikey);
        ResponseEntity<QuoteResponse[]> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, QuoteResponse[].class);
        QuoteResponse[] body = response.getBody();
        return body;
    }
}
