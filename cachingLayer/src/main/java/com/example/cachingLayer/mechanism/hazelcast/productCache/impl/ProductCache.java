package com.example.cachingLayer.mechanism.hazelcast.productCache.impl;

import com.example.cachingLayer.mechanism.hazelcast.productCache.ProductCacheInterface;
import com.example.cachingLayer.mechanism.hazelcast.productCache.ProductEntryListener;
import com.example.cachingLayer.model.dto.Product;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class ProductCache implements ProductCacheInterface{

    public static final String PRODUCT_ENTRY_LISTENER_NAME = "PRODUCT_MAP";
    private final ProductEntryListener productEntryListener;
    private final IMap<String, Product> map;

    public ProductCache(ProductEntryListener productEntryListener, HazelcastInstance hazelcastInstance) {
        this.productEntryListener = productEntryListener;
        this.map = hazelcastInstance.getMap("productCache");
    }

    @Override
    public Product get(String productId) {
        return map.get(productId);
    }

    @Override
    public void set(Product product) {
        map.put(product.getId(), product);
    }

    @Override
    public void subscribeCacheEntryListener() {
        map.addEntryListener(prod, true);
    }
}
