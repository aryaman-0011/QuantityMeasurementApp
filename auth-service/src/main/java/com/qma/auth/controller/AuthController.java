package com.qma.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping({"/auth", "/api/v1/auth"})
public class AuthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("service", "auth-service");
        response.put("status", "UP");
        response.put("message", "Auth service is running");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> info() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("service", "auth-service");
        response.put("loginUrl", "/oauth2/authorization/google");
        response.put("oauth2Callback", "/login/oauth2/code/google");
        response.put("note", "Set GOOGLE_CLIENT_ID and GOOGLE_CLIENT_SECRET before testing OAuth login.");
        return ResponseEntity.ok(response);
    }
}
