package com.ova.plataform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "microservice-template");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, String>> serviceInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("name", "OVA Platform Microservice Template");
        info.put("version", "1.0.0");
        info.put("description", "Template base para microservicios");
        info.put("java.version", System.getProperty("java.version"));
        return ResponseEntity.ok(info);
    }
}