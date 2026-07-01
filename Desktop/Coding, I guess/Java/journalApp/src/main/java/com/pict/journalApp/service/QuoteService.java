package com.pict.journalApp.service;

import com.pict.journalApp.api.response.QuoteResponse;
import com.pict.journalApp.cache.AppCache;
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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public QuoteResponse[] getQuote() {
        String finalAPI = appCache.appCache.get("quote-api").replace("API", apikey);
        ResponseEntity<QuoteResponse[]> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, QuoteResponse[].class);
        QuoteResponse[] body = response.getBody();
        return body;
    }
}
