package com.example.app.Configs.Jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.app.Entities.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class Jwt {
    private static final String secretKey = "200b6d04141310e0747b61f164b7c88asdfasdfxcfasdfcvb2341dsfas";
    
    private static final Long jwtExpiration = 86400000L;

    public String generateToken(UserEntity user) throws Exception {
        Map<String, Object> userClaims = new HashMap<>();
        try {
            if (user.getRoles() != null) {
                userClaims.put("roles", user.getRoles());
            } else {
               System.out.println("faied to get the roesl ");
            }
        } catch (Exception e) {
            throw new Exception("asdf" + e.getMessage());
        }

        return generateTokenWithClaims(userClaims, user);
    }
     public String generateTokenWithClaims(Map<String, Object> extraClaims, UserEntity user) {

        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSiginKey())
                .compact();
    }
     public SecretKey getSiginKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

        return Keys.hmacShaKeyFor(keyBytes);
    }

        public <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSiginKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public List<String> extractRole(String token) {
        Claims claims = extractAllClaims(token);
        return (List<String>) claims.get("roles");
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

      public boolean isTokenValid(String token, UserDetails user) {
        var extractedUsername = extractUsername(token);
        return (extractedUsername.equals(user.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


}
