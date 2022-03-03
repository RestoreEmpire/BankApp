package com.restoreempire.mvc.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class MainWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // WebApplicationInitializer
    // @Override
    // public void onStartup(ServletContext sc) throws ServletException {
    //     AnnotationConfigWebApplicationContext root = 
    //       new AnnotationConfigWebApplicationContext();
        
    //       root.scan("com.restoreempire.mvc");
    //       sc.addListener(new ContextLoaderListener(root));
  

    //       ServletRegistration.Dynamic appServlet = 
    //       sc.addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));
    //         appServlet.setLoadOnStartup(1);
    //         appServlet.addMapping("/");
    // }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {JpaConfig.class};
        // return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}

