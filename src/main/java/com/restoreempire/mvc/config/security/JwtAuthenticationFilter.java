package com.restoreempire.mvc.config.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.common.contenttype.ContentType;

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
        JwtTokenizer tokenizer = new JwtTokenizer(authenticatedUser);
        String token = tokenizer.formToken();
        response.setContentType(ContentType.APPLICATION_JSON.getType());
        Map<String,String> jsonToken = Collections.singletonMap("access_token", token);   
        new ObjectMapper().writeValue(response.getOutputStream(), jsonToken);
    }
    
}
