package com.example.cachingLayer.mechanism;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisCachingMechanism extends CachingMechanism {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ValueOperations<String, Object> valueOperations;

    public RedisCachingMechanism(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public void put(String key, Object value) {
        valueOperations.set(key, value);
    }

    @Override
    public Object get(String key) {
        return valueOperations.get(key);
    }
}
