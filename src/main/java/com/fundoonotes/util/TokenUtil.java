package com.fundoonotes.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class TokenUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    public TokenUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String generateToken(Long userId) {
        String token = "TOKEN_" + userId;
        redisTemplate.opsForValue().set(token, userId, Duration.ofHours(1));
        return token;
    }

    public Long decodeToken(String token) {
        Object value = redisTemplate.opsForValue().get(token);
        if (value == null) {
            throw new RuntimeException("Invalid or expired token");
        }
        return Long.parseLong(value.toString());
    }
}