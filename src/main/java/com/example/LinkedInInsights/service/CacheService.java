package com.example.LinkedInInsights.service;

import com.example.linkedininsights.model.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public CacheService(StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public void saveToCache(String key, Page page) {
        try {
            String jsonData = objectMapper.writeValueAsString(page);
            redisTemplate.opsForValue().set(key, jsonData, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Page getFromCache(String key) {
        try {
            String jsonData = redisTemplate.opsForValue().get(key);
            if (jsonData != null) {
                return objectMapper.readValue(jsonData, Page.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
