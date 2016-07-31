package com.cafeManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Martha on 7/29/2016.
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @RequestMapping(value = "home", method = RequestMethod.GET)
    @RequestMapping(value = "/waiter", method = RequestMethod.GET)
    @RequestMapping(value = "/waiter", method = RequestMethod.POST)
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @RequestMapping(value = "/table", method = RequestMethod.GET)
    @RequestMapping(value = "/table", method = RequestMethod.POST)
    @RequestMapping(value = "/tables/{tableId}", method = RequestMethod.GET)
    @RequestMapping(value = "/tables/{tableId}", method = RequestMethod.POST)

}
