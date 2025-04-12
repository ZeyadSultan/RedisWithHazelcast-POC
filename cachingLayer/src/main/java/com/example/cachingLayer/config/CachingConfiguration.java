package com.example.cachingLayer.config;

import com.example.cachingLayer.condition.IsTZMarketCondition;
import com.example.cachingLayer.condition.NotTZMarketCondition;
import com.example.cachingLayer.mechanism.CachingMechanism;
import com.example.cachingLayer.mechanism.HazelcastCachingMechanism;
import com.example.cachingLayer.mechanism.RedisCachingMechanism;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CachingConfiguration {

    @Bean
    @Conditional(NotTZMarketCondition.class)
    public CachingMechanism redisCachingMechanism(RedisTemplate<String, Object> redisTemplate) {
        return new RedisCachingMechanism(redisTemplate);
    }

    @Bean
    @Conditional(IsTZMarketCondition.class)
    public CachingMechanism hazelcastCachingMechanism(HazelcastInstance hazelcastInstance) {
        IMap<String, Object> map = hazelcastInstance.getMap("map");
        return new HazelcastCachingMechanism(map);
    }
}
