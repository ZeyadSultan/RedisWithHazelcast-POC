package com.example.projectLayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.projectLayer", "com.example.cachingLayer"})
public class ProjectLayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectLayerApplication.class, args);
	}

}
