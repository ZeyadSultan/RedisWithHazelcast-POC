package com.example.cachingLayer.mechanism.hazelcast.productCache;

import com.example.cachingLayer.mechanism.hazelcast.IEntryListener;
import com.example.cachingLayer.model.dto.Product;

public interface ProductCacheInterface extends IEntryListener {
    Product get(String productId);
    void set(Product product);
}
