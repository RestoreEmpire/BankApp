package com.restoreempire.mvc.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig {
    
    DataSource postgresDataSource;

    public WebSecurityConfig(DataSource postgresDataSource) {
        this.postgresDataSource = postgresDataSource;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private void jdbcAuthenticationManagerBuilder(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
        .dataSource(postgresDataSource)
        .passwordEncoder(passwordEncoder())
        .usersByUsernameQuery("select username, password, isActive from bank_user where username = ?")
        .authoritiesByUsernameQuery("select u.username, ur.roles from bank_user u inner join user_role ur on u.id = ur.user_id where u.username = ?");
    }

    @Configuration
    public class FormLoginWebSecurityConfig extends WebSecurityConfigurerAdapter {
    
        @Override
        protected void configure(HttpSecurity http) throws Exception {
           
            http.authorizeRequests()
                .antMatchers("/", "/registration", "/login/**", "/current")
                    .permitAll()
                .anyRequest().authenticated()
                    
                
                // .and()
                // .httpBasic()

                .and()
                .formLogin()
                    .loginPage("/login")
                    // .defaultSuccessUrl("/", true)
                    .permitAll()
                    
                

                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                    .permitAll()
                
                .and()
                .oauth2Login()
                    .loginPage("/login/oauth2")
                    .authorizationEndpoint().baseUri("/login/oauth2/auth")
                    
                ;
                http.httpBasic().and().csrf().disable();

        
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web
                .ignoring()
                    .antMatchers("/view/**");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            jdbcAuthenticationManagerBuilder(auth);
        }

        
        // @Autowired
        // public void configureGlobal(AuthenticationManagerBuilder auth) 
        //   throws Exception {
        //     auth.inMemoryAuthentication().withUser("user")
        //       .password(passwordEncoder().encode("password")).roles("USER");
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
    //     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //         jdbcAuthenticationManagerBuilder(auth);
    //     }
    // }


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

    private ClientRegistration gitHubClientRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
            .clientId("90cd5600600e6af034c9")
            .clientSecret("c4d31d1b0c7725da719269af9a33b2ac58f09243")
            .build();
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
