package com.ygo.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ygo.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.header}")
    private String header;

    // Durata token: 24 ore
    private final long jwtExpirationMs = 24 * 60 * 60 * 1000;

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /** Genera JWT con claims standard e personalizzate */
    public String generateClaims(UserDetails userDetails, User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("idUser", user.getId());
        claims.put("email", user.getEmail());
        return generateClaims(claims, userDetails);
    }

    public String generateClaims(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /** Estrae claim generico dal token */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractUsernameByRequest(HttpServletRequest request) {
        String token = extractToken(request);
        return token != null ? extractUsername(token) : null;
    }

    public String extractUserIdByRequest(HttpServletRequest request) {
        String token = extractToken(request);
        return token != null ? extractUserId(token) : null;
    }

    public String extractUserId(String token) {
        Object id = extractClaim(token, claims -> claims.get("idUser"));
        return id != null ? String.valueOf(id) : null;
    }

    /** Estrae liste di ruoli dal token */
    public List<String> getAuthoritiesFromToken(String token) {
        Claims claims = extractAllClaims(token);
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> authorities = mapper.convertValue(
                claims.get("authorities"),
                new TypeReference<>() {}
        );

        return authorities.stream()
                .map(map -> map.get("authority"))
                .toList();
    }

    /** Estrae il token dall'header Authorization in modo sicuro */
    public String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader(header);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    /** Controlla se il token è valido */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
