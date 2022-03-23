package com.restoreempire.mvc.config.security.filter;

import java.util.Map;

import com.restoreempire.mvc.model.User;
import com.restoreempire.mvc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public class GithubSuccessHandler implements OAuth2AuthorizationSuccessHandler {

    @Autowired
    OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Autowired
    UserService userService;

    @Override
    public void onAuthorizationSuccess(OAuth2AuthorizedClient authorizedClient, Authentication principal,
            Map<String, Object> attributes){
            
            User user = userService.findByGithubId(authorizedClient.getPrincipalName());
            if (user == null) {
                
            }        
    }
    
}
