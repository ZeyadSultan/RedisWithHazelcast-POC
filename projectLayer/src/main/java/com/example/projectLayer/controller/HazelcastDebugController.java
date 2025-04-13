package com.example.projectLayer.controller;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cache/hazelcast")
public class HazelcastDebugController {

    private final HazelcastInstance hazelcastInstance;

    public HazelcastDebugController(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @GetMapping("/all")
    public Map<String, Object> getAllHazelcastEntries() {
        IMap<String, Object> map = hazelcastInstance.getMap("map");
        return new HashMap<>(map);
    }
}

