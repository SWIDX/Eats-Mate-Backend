package com.swidx.userservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${secret.jwt}")
    private String JWT_SECRET;

    // private Long tokenValidTime = 1000L * 60 * 30; // 30min

    @PostConstruct // 라이프사이클 나중에 확인하기
    protected void init() {
        JWT_SECRET = Base64.getEncoder().encodeToString(JWT_SECRET.getBytes());
    }

    public String generateAccessToken(String id, Long tokenValidTime) {
        // Claims claims = Jwts.claims().setSubject(id);
        // claims.put("role", role);
        Date now = new Date();
        return Jwts.builder()
                .setSubject(id) // payload
                .setIssuedAt(now) // generation time
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // expiration time
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET) // algorithm and signature
                .compact();
    }

    public String generateRefreshToken(Long tokenValidTime) {
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now) // generation time
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // expiration time
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET) // algorithm and signature
                .compact();
    }

    // Identify user id from the received token
    public String getTokenOwnerId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // Identify token expiration date from the received token
    public Long getTokenExpirationTime(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration().getTime();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("\n *** JwtUtil: Invalid Token ***");
            System.out.println(e);
        }
        return false;
    }
}
