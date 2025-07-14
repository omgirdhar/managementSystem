package com.example.demo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
    
    private ModelAndView view;

    @GetMapping("/showLogin")
    public Object getMethodName() {
        view = new ModelAndView("login");
        return view;
    }

    @GetMapping("/dashboard")
    public Object getDashBoard(){
        view = new ModelAndView("dashboard");
        return view;
    }
    
}
