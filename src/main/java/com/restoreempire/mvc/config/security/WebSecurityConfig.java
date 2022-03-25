package com.restoreempire.mvc.config.security;

import javax.sql.DataSource;

import com.restoreempire.mvc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

@EnableWebSecurity(debug = true)
public class WebSecurityConfig {
    
    @Autowired
    DataSource postgresDataSource;

    @Autowired
    UserService userService;

    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    OAuth2AuthorizedClientRepository authorizedClientRepository; 

    @Autowired
    OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    PasswordEncoder passwordEncoder;

    
    @Configuration
    public class FormLoginWebSecurityConfig extends WebSecurityConfigurerAdapter {
    
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.csrf().disable();
            http
            .addFilter(new JwtAuthenticationFilter(authenticationManagerBean()))            
            .authorizeRequests()
            
            .antMatchers("/login")
                .permitAll()
            
            .anyRequest()
                .authenticated();
            http
                .oauth2Login()
                .successHandler(new OAuth2AuthenticationSuccessHandler())
                .loginPage("/login/oauth2")
                .authorizationEndpoint().baseUri("/login/oauth2/auth");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider());
        }
        
        @Override
        public void configure(WebSecurity web) throws Exception {
            web
                .ignoring()
                    .antMatchers("/view/**");
        }

    

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }


        // @Override
        // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //     jdbcAuthenticationManagerBuilder(auth);
        // }


        // @Bean
        // @Override
        // protected UserDetailsService userDetailsService() {
        // 	InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(User.withDefaultPasswordEncoder().username("u").password("1").roles("USER").build());
        //     // manager.createUser(User.withDefaultPasswordEncoder().username("u").password("1").roles("USER").build());
        // 	return manager;
        // }
    }

    // @Configuration
    // @Order(1)
    // public class HttpBasicWebSecurityConfig extends WebSecurityConfigurerAdapter {
    //     @Override
    //     protected void configure(HttpSecurity http) throws Exception {
    //         http
    //         .antMatcher("/api/**")
    //             .authorizeRequests()
    //                 .anyRequest()
    //                     .authenticated()

    //         .and()
    //             .httpBasic();
    //     }

    //     @Override
    //     protected void configure(Builder auth) throws Exception {
    //         jdbcAuthenticationManagerBuilder(auth);
    //     }
    // }

    public DaoAuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    // @Configuration
    // @Order(2)
    // public class OAuth2LoginWebSecurityConfig extends WebSecurityConfigurerAdapter {

    //     @Override
    //     protected void configure(HttpSecurity http) throws Exception {
    //         http.authorizeRequests().anyRequest().authenticated()
    //         .and()
    //         .oauth2Login();
    //     }


    // }
}
