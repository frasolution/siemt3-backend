package com.mycompany.siemproject.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String jwt = jwtUtil.getTokenFromRequest(request);
        if (null != jwt && jwtUtil.validateToken(jwt)) {
            processAuthentication(jwt);
        }
        chain.doFilter(request, response);
    }

    private void processAuthentication(String jwt) {
        Authentication resultOfAuthentication = authenticateByToken(jwt);
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
    }

    private Authentication authenticateByToken(String jwt) {
        PreAuthenticatedAuthenticationToken requestAuthentication
                = new PreAuthenticatedAuthenticationToken(jwt, null);
        return authenticate(requestAuthentication);
    }

    private Authentication authenticate(Authentication requestAuthentication) {
        Authentication responseAuthentication
                = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new InternalAuthenticationServiceException(
                    "Unable to authenticate User for provided credentials"
            );
        }
        return responseAuthentication;
    }

}
