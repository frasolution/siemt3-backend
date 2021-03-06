package com.mycompany.siemproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService {

    @Autowired
    private JwtUtil jwtUtil;

    public Authentication retrieve(String token) {
        return createAuthentication(token);
    }

    private Authentication createAuthentication(String token) {
        Authentication auth = createAuth(token);
        return auth;
    }

    private Authentication createAuth(final String token) {
        final String username = jwtUtil.extractUsername(token);
        final String fullName = jwtUtil.extractFullName(token);
        final JwtAuthentication auth
                = new JwtAuthentication(
                        token,
                        username,
                        fullName);
        auth.setAuthenticated(true);
        return auth;
    }
}