package com.restoreempire.mvc.config.security;

import java.util.Date;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtTokenizer {

    private UserDetails user;
    private String secret;

    public JwtTokenizer(UserDetails user) {
        this.user = user;
        secret = "Do-not-use-this-secret-in-prod";
    }

    public JwtTokenizer(UserDetails user, String secret) {
        this.user = user;
        this.secret = secret;
    }

    public String formToken() {
        String token = JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + 1800000L))
            .withClaim(
                "roles",
                user
                    .getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()))
            .sign(Algorithm.HMAC512(secret));
        return token;
    }
}
