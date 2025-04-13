package com.example.projectLayer.Service;

import com.example.cachingLayer.mechanism.CachingMechanism;
import com.example.projectLayer.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProductService {

    private final ApplicationContext context;
    private final CachingMechanism cachingMechanism;

    public ProductService(ApplicationContext applicationContext, CachingMechanism cachingMechanism) {
        this.context = applicationContext;
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

    //ensuring which cachingMechanism is instantiated and it is only one
    @PostConstruct
    public void listAllCachingBeans() {
        String[] cachingBeans = context.getBeanNamesForType(CachingMechanism.class);
        System.out.println("CachingMechanism beans registered:");
        for(String beanName : cachingBeans) {
            System.out.println(beanName);
        }
    }

}
