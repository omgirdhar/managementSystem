package com.example.demo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class SettingsController {
    
    @GetMapping("/settings")
    public Object getSettingsPage(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("settings");
        try {
            return view;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return view;
    }
    
}
