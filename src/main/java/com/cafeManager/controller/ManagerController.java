package com.cafeManager.controller;

import com.cafeManager.dto.ProductDTO;
import com.cafeManager.dto.TableDTO;
import com.cafeManager.dto.UserDTO;
import com.cafeManager.exception.*;
import com.cafeManager.service.ProductService;
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
@RequestMapping("/manager")
public class ManagerController {

    public static final String WAITERS = "waiters";
    public static final String NO_WAITERS = "no_waiters";
    public static final String TABLES = "tables";
    public static final String NO_TABLES = "no_tables";
    public static final String PRODUCTS = "products";
    public static final String NO_PRODUCTS = "no_products";

    @Autowired
    UserService userService;

    @Autowired
    TableService tableService;

    @Autowired
    ProductService productService;

    @Autowired
    Environment environment;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView homePage(){
        ModelAndView modelAndView = new ModelAndView();
        List<UserDTO> waiters = null;
        List<TableDTO> tables = null;
        List<ProductDTO> products = null;

        try {
            waiters = userService.getAllUsersByRole(environment.getProperty("role_waiter"));
            tables = tableService.getAllTables();
            products = productService.getAllProducts();
        } catch (NoSuchRoleException e) {
            e.printStackTrace();
            modelAndView.setViewName("global_error");
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            modelAndView.addObject(NO_WAITERS, "No waiters yet");
        } catch (NoSuchTableException e) {
            e.printStackTrace();
            modelAndView.addObject(NO_TABLES, "No tables yet");
        } catch (NoSuchProductException e) {
            e.printStackTrace();
            modelAndView.addObject(NO_PRODUCTS, "No products yet");
        } catch (NullOrEmptyArgumentsException e) {
            e.printStackTrace();
            modelAndView.setViewName("global_error");
        }finally {
            if(waiters != null) modelAndView.addObject(WAITERS, waiters);
            if(waiters != null) modelAndView.addObject(TABLES, tables);
            if(waiters != null) modelAndView.addObject(PRODUCTS, products);
        }
        return modelAndView;
    }
    @RequestMapping(value = "/waiter", method = RequestMethod.GET)
    public ModelAndView waiterCreationPage(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(value = "/waiter", method = RequestMethod.POST)
    public ModelAndView createWaiter(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView productCreationPage(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ModelAndView createProduct(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(value = "/table", method = RequestMethod.GET)
    public ModelAndView tableCreationPage(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(value = "/table", method = RequestMethod.POST)
    public ModelAndView createTable(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    @RequestMapping(value = "/tables/{tableId}", method = RequestMethod.GET)
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
