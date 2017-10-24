package com.vines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SimpleController {
    static final private
    List<Customer> customers = new ArrayList<Customer>();
    @RequestMapping("/")
    String home(ModelMap map){
        customers.add(new Customer(1L, "siva01", 12));
        customers.add(new Customer(2L, "siva02", 18));
        customers.add(new Customer(3L, "siva10", 25));
        map.addAttribute("data", customers);
        return "hello";
    }
}
