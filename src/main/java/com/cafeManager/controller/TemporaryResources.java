package com.cafeManager.controller;

import com.cafeManager.service.TableService;
import com.cafeManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Martha on 7/29/2016.
 */
@Controller
@RequestMapping("/test")
public class TemporaryResources {

    @Autowired
    UserService userService;

    @Autowired
    TableService tableService;

    @Autowired
    Environment environment;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView addRole(){
        userService.createRole(environment.getProperty("role_waiter"));
        userService.createRole(environment.getProperty("role_manager"));
        userService.createUser("Karen", "aa", environment.getProperty("role_waiter"));
        userService.createUser("Karine", "aa", environment.getProperty("role_waiter"));
        userService.createUser("John", "aa", environment.getProperty("role_waiter"));


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }



}
