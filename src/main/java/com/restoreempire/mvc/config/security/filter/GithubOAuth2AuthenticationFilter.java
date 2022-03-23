package com.restoreempire.mvc.config.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restoreempire.mvc.model.User;
import com.restoreempire.mvc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

public class GithubOAuth2AuthenticationFilter extends OAuth2LoginAuthenticationFilter {

    @Autowired
    UserService userService;

    AuthenticationManager authenticationManager;

    OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    public GithubOAuth2AuthenticationFilter(AuthenticationManager authenticationManager,
        OAuth2AuthorizedClientService oAuth2AuthorizedClientService,
        ClientRegistrationRepository clientRegistrationRepository
    ) {
        super(clientRegistrationRepository, oAuth2AuthorizedClientService);
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.authenticationManager = authenticationManager;
        setAuthenticationManager(authenticationManager);
    }


    @Override
    protected void successfulAuthentication(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain chain,
        Authentication authResult
    ) 
        throws IOException, ServletException {

            OAuth2AuthorizedClient authorizedClient =
            oAuth2AuthorizedClientService.loadAuthorizedClient("github", authResult.getName());
            
            User user = userService.findByGithubId(authorizedClient.getPrincipalName());
            if (user == null) {
                response.sendRedirect("/registration");
            }
    }
}

