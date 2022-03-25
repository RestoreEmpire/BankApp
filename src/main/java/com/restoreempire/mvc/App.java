package com.restoreempire.mvc;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class App extends SpringBootServletInitializer{

    // @Override
    // protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    //     return application.sources(App.class);
    // }    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "9090"));
        app.run(args);
    }
}
