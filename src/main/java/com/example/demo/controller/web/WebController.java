package com.example.demo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WebController {
    
    @GetMapping("/")
    public Object getHome() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }
    
}
