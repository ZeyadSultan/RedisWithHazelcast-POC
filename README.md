# Redis and Hazelcast PoC

## Overview

This repository demonstrates a **proof of concept (PoC)** for combining both **Redis** and **Hazelcast** caching solutions into a Spring Boot project. The objective is to showcase how we can connect and switch between these caching solutions based on environment variables, making it easier to switch between Redis and Hazelcast as the caching mechanism in the project.
Where i have product controller and service where i can get and put products
and to check what components each cache holds i just have created an api for each to get components inside

## Architecture

The architecture consists of the following components:

- **Redis**: A fast, in-memory key-value store for caching data.
- **Hazelcast**: A distributed in-memory object store and computing platform.
- **Spring Boot Application**: The core application that connects to Redis or Hazelcast, depending on the environment variable.

### **Environment Variables**

Based on the market variable in the app.properties
If it is tanzania -> Hazelcast
Else -> Redis

## Prerequisites

Before running the application, ensure you have the following installed:

- **Docker**: For running Redis and Hazelcast containers.
- **Java 21 or above**: For running the Spring Boot application.
- **Maven**: For building the project.

## Setup Instructions

### **1. Clone the Repository**

Clone the repository to your local machine:

```bash
git clone https://github.com/your-organization/RedisWithHazelcast-POC.git
cd RedisWithHazelcast-POC
```

### **2. Build the project**
```bash
cd projectLayer
mvn clean install
```

### **3. Run docker container to run the hazelcast and redis instance**
```bash
docker-compose up -d
```

### **4. Set the market variable in the application.properties in the projectLayer**
```bash
market=tanzania
```

### **5. Run the project**


## Code snippets
In the `ProductService` within the **projectLayer**, we use a `@PostConstruct` method to ensure that only one caching mechanism is instantiated and available. This method prints the names of all beans of type `CachingMechanism` to verify that the correct caching mechanism (either Redis or Hazelcast) is being used.
Explanation:

This method runs after the bean is initialized, ensuring that the appropriate caching mechanism is instantiated at runtime.

The code checks all CachingMechanism beans in the application context and prints them. This ensures only the expected bean is available for use, either the Redis or Hazelcast implementation, based on the environment variable.
```java
@PostConstruct
public void listAllCachingBeans() {
    // Retrieve the names of all beans of type CachingMechanism in the application context
    String[] cachingBeans = context.getBeanNamesForType(CachingMechanism.class);
    System.out.println("CachingMechanism beans registered:");
    for (String beanName : cachingBeans) {
        // Print the name of each caching mechanism bean registered in the application context
        System.out.println(beanName);
    }
}
```

In the cachingLayer, we define beans for both Redis and Hazelcast. These beans are conditionally instantiated based on the market conditions (whether it's TZ market or not). The beans are registered using @Conditional annotations, which ensure that only the relevant caching mechanism is created at runtime.

Redis Caching Bean:
```java
@Bean
@Conditional(NotTZMarketCondition.class)  // Only instantiate this bean if the condition is met (NotTZMarket)
public CachingMechanism redisCachingMechanism(RedisTemplate<String, Object> redisTemplate) {
    // Create a Redis caching mechanism using the Redis template
    return new RedisCachingMechanism(redisTemplate);
}

@Bean
@Conditional(IsTZMarketCondition.class)  // Only instantiate this bean if the condition is met (IsTZMarket)
public CachingMechanism hazelcastCachingMechanism(HazelcastInstance hazelcastInstance) {
    // Get a map from Hazelcast instance to be used as a distributed cache
    IMap<String, Object> map = hazelcastInstance.getMap("map");
    // Create a Hazelcast caching mechanism using the distributed map
    return new HazelcastCachingMechanism(map);
}
```


## **Examples from PostMan**

### **When market=tanzania so hazelcast bean is instantiated**
<img width="593" alt="Screenshot 2025-04-13 at 9 14 24 PM" src="https://github.com/user-attachments/assets/091514e5-08c2-4ad2-974e-eb3a06312f70" />

<img width="593" alt="Screenshot 2025-04-13 at 9 14 38 PM" src="https://github.com/user-attachments/assets/8fec34c8-ad96-499d-848d-a27a64f173b8" />

<img width="593" alt="Screenshot 2025-04-13 at 9 14 50 PM" src="https://github.com/user-attachments/assets/0c9fc450-d46c-48d9-a3cb-158ad00f0d10" />


### **When market=Egypt so redis bean is instantiated**

<img width="593" alt="Screenshot 2025-04-13 at 9 21 27 PM" src="https://github.com/user-attachments/assets/6f492521-37d5-4fa7-99ec-47301a3eef2c" />

<img width="593" alt="Screenshot 2025-04-13 at 9 21 59 PM" src="https://github.com/user-attachments/assets/c3d49286-9b9b-4e1c-b9bb-31cfe11e15d0" />

<img width="593" alt="Screenshot 2025-04-13 at 9 21 39 PM" src="https://github.com/user-attachments/assets/06115a31-60db-45df-b180-2f375608809a" />








