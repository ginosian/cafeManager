package com.cafeManager.configuration;

import com.cafeManager.configuration.Configurations;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Martha on 7/29/2016.
 */
public class Initializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Creates context object
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        // Registers annotated configurations class
        ctx.register(Configurations.class);

        // Sets ContextLoaderListener to servletContext
        servletContext.addListener(new ContextLoaderListener(ctx));

        // Passes servlet context to context instance
        ctx.setServletContext(servletContext);

        //Registers dispatch servlet and passes context instance
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));

        //Maps URL pattern
        servlet.addMapping("/");

        //Sets creation priority
        servlet.setLoadOnStartup(1);

        //Registers security filters
        //TODO uncomment as soon as authentication logic will be implemented
//        FilterRegistration.Dynamic security = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());

        // Sets dispatcher types a security filters to be applied
        //TODO uncomment as soon as authentication logic will be implemented
//        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
//        security.addMappingForUrlPatterns(dispatcherTypes, true, "/**");
    }



}
