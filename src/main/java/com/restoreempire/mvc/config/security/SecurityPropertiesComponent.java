package com.restoreempire.mvc.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SecurityPropertiesComponent {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.gitHubClientRegistration());
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(
            ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Bean
    public OAuth2AuthorizedClientRepository authorizedClientRepository(
            OAuth2AuthorizedClientService authorizedClientService) {
        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
    }

    @Bean
    public ClientRegistration gitHubClientRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
            .clientId("90cd5600600e6af034c9")
            .clientSecret("c4d31d1b0c7725da719269af9a33b2ac58f09243")
            .build();
    }

    @Bean
    public OAuth2AuthorizationRequestRedirectFilter redirectFilter() {
        return new OAuth2AuthorizationRequestRedirectFilter(clientRegistrationRepository());

    }

    @Bean
    public OAuth2AuthorizedClientProvider oAuth2AuthorizedClientProvider() { 
        OAuth2AuthorizedClientProvider oAuth2AuthorizedClientProvider = 
        OAuth2AuthorizedClientProviderBuilder.builder()
            .authorizationCode()
            .refreshToken()
            .clientCredentials()
            .password()
            .build();
            return oAuth2AuthorizedClientProvider;
    }
    // @Bean
    // public OAuth2LoginAuthenticationFilter authFilter() {
    //     return new OAuth2LoginAuthenticationFilter(this.clientRegistrationRepository(), this.authorizedClientService(clientRegistrationRepository()));
    // }
}
