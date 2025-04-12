package com.example.cachingLayer.mechanism;

import com.hazelcast.map.IMap;

public class HazelcastCachingMechanism extends CachingMechanism {

    private final IMap<String, Object> map;

    public HazelcastCachingMechanism(IMap<String, Object> map) {
        this.map = map;
    }

    @Override
    public void put(String key, Object value) {
        map.put(key, value);
    }

    @Override
    public Object get(String key) {
        return map.get(key);
    }
}
