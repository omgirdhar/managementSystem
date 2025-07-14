package com.example.demo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class DepartmentsController {
    
    @GetMapping("/departments")
    public Object getDepartmentsPage(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("superAdminPages/setupDepartments");
        try{
            return view;
        }catch(Exception e){
            e.printStackTrace();
        }
        return view;
    }
    
}
