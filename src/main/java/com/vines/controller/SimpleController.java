package com.vines.controller;

import com.vines.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.expression.Lists;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SimpleController {

    @RequestMapping("/hello")
    String home(ModelMap map){
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer(1L, "siva01", 12));
        customers.add(new Customer(2L, "siva02", 18));
        customers.add(new Customer(3L, "siva10", 25));
        map.addAttribute("data", customers);
        return "hello";
    }

    @RequestMapping("/login")
    String login(ModelMap map){
        return "login";
    }

    @RequestMapping(value = "/loginIn",method = {RequestMethod.GET,RequestMethod.POST})
    String submit(ModelMap map, User user,HttpSession session){
        session.setAttribute("user",user);
        return "table";
    }
}
