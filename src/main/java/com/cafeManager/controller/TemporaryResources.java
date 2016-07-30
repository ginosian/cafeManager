package com.cafeManager.controller;

import com.cafeManager.dto.UserDTO;
import com.cafeManager.exception.RoleExistException;
import com.cafeManager.exception.UserExistException;
import com.cafeManager.service.TableService;
import com.cafeManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        if(i) {
            try {
                userService.createRole(environment.getProperty("role_waiter"));
                userService.createRole(environment.getProperty("role_manager"));
            } catch (RoleExistException e) {
                e.printStackTrace();
            }
            try {
                userService.createUser("Karen", "aa", environment.getProperty("role_waiter"));
                userService.createUser("Karine", "aa", environment.getProperty("role_waiter"));
                userService.createUser("John", "aa", environment.getProperty("role_waiter"));
            } catch (UserExistException e) {
                e.printStackTrace();
                modelAndView.setViewName("exception_user_exist");
                return modelAndView;
            }
            i = false;
        }
        List<UserDTO> userDTOs = userService.getAllUsersByRole(environment.getProperty("role_waiter"));
        for (int i = 0; i < userDTOs.size(); i++) {
            System.out.println("***********" + userDTOs.get(i).getUsername() + "**************");
        }

        modelAndView.setViewName("index");
        return modelAndView;
    }



}
