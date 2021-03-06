package com.mycompany.siemproject.security;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthentication implements Authentication {

    private String token;
    private String username;
    private boolean authenticated;
    private String fullName;

    public JwtAuthentication() {
    }

    public JwtAuthentication(
            String token,
            String username,
            String fullName) {
        this.token = token;
        this.username = username;
        this.fullName = fullName;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public String getName() {
        return fullName;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        authenticated = isAuthenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public Object getCredentials() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}