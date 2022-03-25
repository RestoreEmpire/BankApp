package com.restoreempire.mvc.config.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.common.contenttype.ContentType;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        UserDetails authenticatedUser = (UserDetails) authentication.getPrincipal();
        JwtTokenizer tokenizer = new JwtTokenizer(authenticatedUser);
        String token = tokenizer.formToken();
        response.setContentType(ContentType.APPLICATION_JSON.getType());
        Map<String,String> jsonToken = Collections.singletonMap("access_token", token);   
        new ObjectMapper().writeValue(response.getOutputStream(), jsonToken);
                    
    }
    
}
