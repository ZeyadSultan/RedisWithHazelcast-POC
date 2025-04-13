package com.example.projectLayer.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cache/redis")
public class RedisDebugController {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisDebugController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/all")
    public Map<String, Object> getAllRedisKeys() {
        Set<String> keys = redisTemplate.keys("*");
        if (keys == null || keys.isEmpty()) return Map.of();
        return keys.stream().collect(Collectors.toMap(
                Function.identity(),
                key -> redisTemplate.opsForValue().get(key)
        ));
    }
}

