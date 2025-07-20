package com.example.demo.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constants.ApplicationConstants;
import com.example.demo.model.Department;
import com.example.demo.model.User;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class DepartmentsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DepartmentService departmentService;
    
    @GetMapping("/departments")
    private Object getDepartmentsPage(HttpSession session) {
        ModelAndView view = new ModelAndView("superAdminPages/setupDepartments");
        try{
        	User currentUser = (User) session.getAttribute(ApplicationConstants.LoggedUser);
        	if(currentUser == null) {
        		session.invalidate();
        		return "redirect:/showLogin";
        	}
        	view.addObject("department",new Department());
        	view.addObject("departments", departmentService.getAllDepartments());
        	view.addObject("users", userService.getAllUsers());
        }catch(Exception e){
            e.printStackTrace();
        }
        return view;
    }
    
    @PostMapping("/createDepartment")
    public String createDepartment(@ModelAttribute("department") Department department,
                                   HttpSession session, RedirectAttributes attrs) {
    	try {
    		if (departmentService.getDepartmentByName(department.getDepartmentName()) != null) {
                attrs.addFlashAttribute("msg","Department already exists.");
            } else {
                departmentService.saveDepartment(department);
                attrs.addFlashAttribute("msg","Department created successfully.");
            }
    	}catch (Exception e) {
			e.printStackTrace();
		}
        return "redirect:/departments";
    }

    @PostMapping("/updateDepartment")
    public String updateDepartment(@ModelAttribute("department") Department dto,
                                   HttpSession session, RedirectAttributes attrs) {
    	try {
    		Department existing = departmentService.getDepartmentById(dto.getId());
            if (existing != null) {
                existing.setDepartmentName(dto.getDepartmentName());
                departmentService.saveDepartment(existing);
                attrs.addFlashAttribute("msg","Department updated successfully.");
            } else {
                attrs.addFlashAttribute("msg","Department not found.");
            }
    	}catch (Exception e) {
			e.printStackTrace();
		}
        return "redirect:/departments";
    }

    @PostMapping("/deleteDepartment")
    public String deleteDepartment(@RequestParam("id") long id,
                                   HttpSession session, RedirectAttributes attrs) {
    	try {
    		Department d = departmentService.getDepartmentById(id);
            if (d != null) {
                departmentService.deleteDepartment(d);
                attrs.addFlashAttribute("msg","Department deleted successfully.");
            } else {
                attrs.addFlashAttribute("msg","Department not found.");
            }
    	}catch (Exception e) {
			e.printStackTrace();
		}
        return "redirect:/departments";
    }
    
}
