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
@RequestMapping("")
public class TemporaryResources {

    public static boolean i = true;

    @Autowired
    UserService userService;

    @Autowired
    TableService tableService;

    @Autowired
    Environment environment;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView addRole() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }



}
