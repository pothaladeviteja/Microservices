package com.example.AuthServer.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    public String SECRETKEY="GFHESDHJUHKJ5R76289UYGJBSXCHUJABCD";
    public String generateToken(String name,String role) {
        String token = Jwts.builder()
                .subject(name)
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .signWith(signinKey())
                .compact();
        return token;
    }

    public SecretKey signinKey()
    {
        System.out.println(Keys.hmacShaKeyFor(SECRETKEY.getBytes(StandardCharsets.UTF_8)));
        return Keys.hmacShaKeyFor(SECRETKEY.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUserName(String token)
    {
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token)
    {
        return extractAllClaims(token).get("role").toString();
    }

    private Claims extractAllClaims(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(signinKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims;
    }

    public boolean isTokenValid(String token)
    {
        return extractAllClaims(token).getExpiration().after(new Date());
    }
}
