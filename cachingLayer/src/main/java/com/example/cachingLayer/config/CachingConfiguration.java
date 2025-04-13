package com.example.cachingLayer.config;

import com.example.cachingLayer.condition.IsTZMarketCondition;
import com.example.cachingLayer.condition.NotTZMarketCondition;
import com.example.cachingLayer.mechanism.CachingMechanism;
import com.example.cachingLayer.mechanism.HazelcastCachingMechanism;
import com.example.cachingLayer.mechanism.RedisCachingMechanism;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class CachingConfiguration {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("dev");
        clientConfig.getNetworkConfig().addAddress("localhost:5701");
        return HazelcastClient.newHazelcastClient(clientConfig);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }


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
