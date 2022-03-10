package com.restoreempire.mvc.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    @Qualifier("postgresDataSource")
    DriverManagerDataSource postgresDataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
            .antMatchers("/", "/registration")
                .permitAll()
            .anyRequest()
                .authenticated()
                
            .and()
            .formLogin().loginPage("/login")
            .defaultSuccessUrl("/", true)
            .failureUrl("/login?error=true")
                .permitAll()
                
            .and()
            .logout()
                .permitAll();
            
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(postgresDataSource)
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
            .usersByUsernameQuery("select username, password, isActive from bank_user where username = ?")
            .authoritiesByUsernameQuery("select u.username, ur.roles from bank_user u inner_join user_role ur on u.id = ur.user_id where u.username = ?");
    }


    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) 
    //   throws Exception {
    //     auth.inMemoryAuthentication().withUser("user")
    //       .password(passwordEncoder().encode("password")).roles("USER");
    // }

    //     @Bean
    //     public PasswordEncoder passwordEncoder() {
    //         return new BCryptPasswordEncoder();
    // }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
		return manager;
    }
}
