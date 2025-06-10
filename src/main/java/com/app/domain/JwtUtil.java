package com.app.domain;

import io.jsonwebtoken.*;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationTime}")
    Integer expirationTime;

    @Value("${jwt.expirationTimeRefreshToken}")
    Integer expirationTimeRefreshToken;

    @Value("${jwt.issuer}")
    String jwtIssuer;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        Assert.hasText(secret);
        Assert.hasText(String.valueOf(expirationTime));
        Assert.hasText(String.valueOf(expirationTimeRefreshToken));
        Assert.hasText(jwtIssuer);
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            String issuer = claims.getIssuer();
            Date expiration = claims.getExpiration();

            return username.equals(userDetails.getUsername())
                    && issuer.equals(jwtIssuer)
                    && expiration.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            // Signature invalid, token malformed, expired, etc.
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            Date expiration = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException | IllegalArgumentException e) {
            return true;
        }
    }

    public String generateAccessToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * expirationTime))
                .setIssuedAt(new Date())
                .setIssuer(jwtIssuer)
                .setId(UUID.randomUUID().toString())
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * expirationTimeRefreshToken))
                .setIssuer(jwtIssuer)
                .setId(UUID.randomUUID().toString())
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}