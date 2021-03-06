package com.mycompany.siemproject.controller;

import com.mycompany.siemproject.security.AuthenticationRequest;
import com.mycompany.siemproject.security.AuthenticationResponse;
import com.mycompany.siemproject.security.JwtUtil;
import com.mycompany.siemproject.security.SiemUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authReq) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
        final SiemUserDetails details = (SiemUserDetails) auth.getPrincipal();
        final String jwt = jwtUtil.generateToken(details);
        return new AuthenticationResponse(jwt);
    }
}
