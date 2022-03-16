package com.restoreempire.mvc.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class MvcWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
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
        return new Class[] {
            WebSecurityConfig.FormLoginWebSecurityConfig.class,
            // WebSecurityConfig.HttpBasicWebSecurityConfig.class,
            // WebSecurityConfig.OAuth2LoginWebSecurityConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}

