package com.cafeManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Martha on 7/29/2016.
 */
@Controller
@RequestMapping("/waiter")
public class WaiterController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homePage(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(value = "/table/{tableId}", method = RequestMethod.GET)
    public ModelAndView tableDetailPage(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(value = "/tables/{tableId}", method = RequestMethod.POST)
    public ModelAndView updateTableDetail(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
