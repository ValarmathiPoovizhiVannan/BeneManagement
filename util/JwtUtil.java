package com.example.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET =
            "mySecretKeymySecretKeymySecretKey123456";

    public String generateToken(String corpCrn) {

        return Jwts.builder()
                .subject(corpCrn)
                .issuedAt(new Date())
                .expiration(new Date(
                        System.currentTimeMillis() + 86400000))
                .signWith(Keys.hmacShaKeyFor(
                        SECRET.getBytes()))
                .compact();
    }

    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(
                                SECRET.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}