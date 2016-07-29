package com.cafeManager.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Martha on 7/29/2016.
 */
public class SecuritySuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(response.isCommitted()) return;
        response.sendRedirect(targetURL(authentication));
    }

    public String targetURL(Authentication authentication){
        String targetUrl = "";
        if(authentication.getAuthorities().contains("WAITER")){
            targetUrl = "/waiter";
        }

        if(authentication.getAuthorities().contains("MANAGER")){
            targetUrl = "/manager";
        }
        return targetUrl;
    }
}
