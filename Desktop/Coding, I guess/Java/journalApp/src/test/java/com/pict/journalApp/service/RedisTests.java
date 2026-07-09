package com.pict.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testSendMail() {
        redisTemplate.opsForValue().set("Email","abc123@gmail.com");
        Object email = redisTemplate.opsForValue().get("Email");
        System.out.println(email);
        int a = 1;
    }
}
