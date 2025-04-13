package com.example.projectLayer.Service;

import com.example.cachingLayer.mechanism.CachingMechanism;
import com.example.projectLayer.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    private final CachingMechanism cachingMechanism;

    public ProductService(CachingMechanism cachingMechanism) {
        this.cachingMechanism = cachingMechanism;
    }

    public void addProduct(Product product) {
        cachingMechanism.put(product.getId(), product);
    }

    public Product getProduct(String id) {
        Object product = cachingMechanism.get(id);
        if (product instanceof Product) {
            return (Product) product;
        }
        return null;
    }

}
