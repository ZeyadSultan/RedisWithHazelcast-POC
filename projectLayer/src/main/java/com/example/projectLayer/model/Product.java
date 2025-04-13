package com.example.projectLayer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    private String id;
    private String name;

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
