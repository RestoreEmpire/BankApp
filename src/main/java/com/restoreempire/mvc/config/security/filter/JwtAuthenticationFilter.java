package com.restoreempire.mvc.config.security.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.crypto.impl.HMAC;
import com.restoreempire.mvc.model.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request, 
        HttpServletResponse response)
            throws AuthenticationException {
        
        String username = obtainUsername(request);
		username = (username != null) ? username : "";
		username = username.trim();
		String password = obtainPassword(request);
		password = (password != null) ? password : "";
        UsernamePasswordAuthenticationToken authToken = 
        new UsernamePasswordAuthenticationToken(
            username, password);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        UserDetails authenticatedUser = (UserDetails) authResult.getPrincipal();
        String token = JWT.create()
            .withSubject(authenticatedUser.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + 1800000L))
            .sign(Algorithm.HMAC512("Do-not-use-this-secret-in-prod"));
            
        response.addHeader("Authorization", "Bearer " + token);
    }
    
}
