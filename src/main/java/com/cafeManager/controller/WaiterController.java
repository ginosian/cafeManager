package com.cafeManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Martha on 7/29/2016.
 */
@Controller
@RequestMapping("/waiter")
public class WaiterController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @RequestMapping(value = "/table/{tableId}", method = RequestMethod.GET)
    @RequestMapping(value = "/tables/{tableId}", method = RequestMethod.POST)
}
