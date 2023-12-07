package com.example.signature.service;

import org.springframework.stereotype.Service;

import com.example.signature.config.AuthenticationProperties;

@Service
public class AuthenticationService {
    
    private final AuthenticationProperties authenticationProperties;

    public AuthenticationService(AuthenticationProperties authenticationProperties) {
        this.authenticationProperties = authenticationProperties;
    }

    public boolean authenticate(String username, String password) {
    
        return authenticationProperties.getUsername().equals(username)
                && authenticationProperties.getPassword().equals(password);
    }
    
}
