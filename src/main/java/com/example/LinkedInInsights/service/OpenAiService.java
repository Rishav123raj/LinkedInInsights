package com.example.LinkedInInsights.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAiService {

    private static final String API_URL = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "YOUR_OPENAI_API_KEY";

    public String generateSummary(String pageDetails) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = new HashMap<>();
        request.put("model", "gpt-4");
        request.put("prompt", "Summarize this LinkedIn page: " + pageDetails);
        request.put("max_tokens", 100);
        
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + API_KEY);
        headers.put("Content-Type", "application/json");

        return restTemplate.postForObject(API_URL, request, String.class);
    }
}
