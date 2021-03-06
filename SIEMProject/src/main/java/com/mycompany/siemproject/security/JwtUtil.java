package com.mycompany.siemproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
    private static final String FULL_NAME_FIELD = "fullName";

    @Value("${jwt.validityDuration}")
    private int validityDuration;
    
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(SiemUserDetails details) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, details.getUsername(), details.getName());
    }

    private String createToken(Map<String, Object> claims, String username, String fullName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .claim(FULL_NAME_FIELD, fullName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validityDuration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            LOGGER.error("Invalid JWT signature: " + ex);
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token: " + ex);
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token: " + ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token: " + ex);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty. " + ex);
        }
        return false;

    }

    public String getTokenFromRequest(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (null != authorizationHeader && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    public String extractFullName(String token) {
        return extractClaim(token, claims -> claims.get(FULL_NAME_FIELD, String.class));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}