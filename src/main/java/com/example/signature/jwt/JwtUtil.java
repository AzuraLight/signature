package com.example.signature.jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
    
    private static final KeyPair keyPair = generateKeyPair();

    private static KeyPair generateKeyPair() {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate key pair");
        }
    }
    
    public static String generateToken(String username) {
    
        return Jwts.builder()
                .setSubject(username) // 발급자
                .setIssuedAt(new Date()) // 발급일자
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
    
    }
}
