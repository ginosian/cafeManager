package com.cafeManager.controller;

import com.cafeManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Martha on 7/29/2016.
 */
@Controller
@RequestMapping("")
public class AuthController {
    @Autowired
    Environment environment;

    @Autowired
    UserService userService;

    @RequestMapping(value = "")
    public ModelAndView root(){
        userService.initDefaultUsers();
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(Authentication authentication){
        if (authentication != null) {
            if (authentication.getAuthorities().iterator().next().toString().equals("ROLE_" + environment.getProperty("role_waiter"))){
                return new ModelAndView("redirect:/waiter");}
            else { return new ModelAndView("redirect:/manager"); }
        }
        return new ModelAndView("login_page");
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return new ModelAndView("redirect:/login");
    }

}
