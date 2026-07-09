package com.pict.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class redisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void get(String key) {
        Object o = redisTemplate.opsForValue().get(key);
    }
}
