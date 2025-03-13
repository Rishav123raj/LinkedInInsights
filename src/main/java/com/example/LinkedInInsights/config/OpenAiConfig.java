package com.example.LinkedInInsights.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class OpenAiConfig {

    @Value("${openai.api.key}")
    private String openAiApiKey;

    @Value("${openai.api.url}")
    private String openAiApiUrl;

    @Bean
    public RestTemplate openAiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    public HttpHeaders getOpenAiHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(openAiApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    public String getOpenAiApiUrl() {
        return openAiApiUrl;
    }
}
