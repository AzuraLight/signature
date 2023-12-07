package com.example.signature.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.signature.jwt.JwtUtil;
import com.example.signature.service.AuthenticationService;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {

        String username = credentials.get("username");
        String password = credentials.get("password");

        if (authenticationService.authenticate(username, password)) {

            String jwtToken = JwtUtil.generateToken(username);
            Map<String, String> response = new HashMap<String, String>();
            response.put("token", jwtToken);
            return ResponseEntity.ok(response);

        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }

    }
}
