package com.restoreempire.mvc.config.security;

import javax.sql.DataSource;

import com.restoreempire.mvc.config.security.filter.GithubOAuth2AuthenticationFilter;
import com.restoreempire.mvc.config.security.filter.GithubSuccessHandler;
import com.restoreempire.mvc.config.security.filter.JwtAuthenticationFilter;
import com.restoreempire.mvc.config.security.filter.JwtAuthorizationFilter;
import com.restoreempire.mvc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthorizationCodeAuthenticationProvider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

    // @Autowired
    // GithubOAuth2AuthenticationFilter authFilter;
    
    // @Autowired
    // OAuth2AuthorizationRequestRedirectFilter redirectFilter;
    
    // @Autowired
    // GithubSuccessHandler githubSuccessHandler;

    // public OAuth2AuthorizationCodeAuthenticationProvider

    // private void jdbcAuthenticationManagerBuilder(AuthenticationManagerBuilder auth) throws Exception{
    //     auth.jdbcAuthentication()
    //     .dataSource(postgresDataSource)
    //     .passwordEncoder(passwordEncoder())
    //     .usersByUsernameQuery("select username, password, isActive from bank_user where username = ?")
    //     .authoritiesByUsernameQuery("select u.username, ur.roles from bank_user u inner join user_role ur on u.id = ur.user_id where u.username = ?");
    // }

    @Configuration
    public class FormLoginWebSecurityConfig extends WebSecurityConfigurerAdapter {
    
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // GithubOAuth2AuthenticationFilter authFilter =
            // new GithubOAuth2AuthenticationFilter(
            //     authenticationManagerBean(),
            //     authorizedClientService,
            //     clientRegistrationRepository 
            // );
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.csrf().disable();
            http
            // .addFilter(authFilter)
            // .addFilter(redirectFilter)
            .addFilter(new JwtAuthenticationFilter(authenticationManagerBean()))
            .addFilter(new JwtAuthorizationFilter(authenticationManagerBean(), userService))
            // .antMatcher("/login")
            .authorizeRequests()
            
            .antMatchers("/", "/registration", "/login/**", "/current", "/oauth2/**")
            .permitAll()
            .anyRequest().authenticated()
            
            
            
            // .and()
            // .formLogin()
            // .loginPage("/login")
            // // .defaultSuccessUrl("/", true)
            // .permitAll()
            
            // .and()
            // .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
            // .permitAll()

            // .and()            
            // .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            ;
                
            // http.httpBasic();
            // http.antMatcher("/login")
            // .csrf().disable();
            // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            // http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
            // http.addFilter(new JwtAuthorizationFilter(authenticationManagerBean(), userService));
            // http.oauth2Login()
            // // .successHandler(new GithubSuccessHandler())
            // .loginPage("/login/oauth2")
            // .authorizationEndpoint().baseUri("/login/oauth2/auth");
            // http.oauth2Client()
            //     .clientRegistrationRepository(clientRegistrationRepository)
            //     .authorizedClientRepository(authorizedClientRepository)
            //     .authorizedClientService(authorizedClientService);            
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
