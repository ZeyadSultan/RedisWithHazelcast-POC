package com.example.cachingLayer.mechanism;

public abstract class CachingMechanism {
    public abstract void put(String key, Object value);
    public abstract Object get(String key);
}
