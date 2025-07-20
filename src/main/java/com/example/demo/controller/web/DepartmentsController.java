package com.example.demo.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constants.ApplicationConstants;
import com.example.demo.model.Departments;
import com.example.demo.model.User;
import com.example.demo.service.DepartmentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class DepartmentsController {
	
	@Autowired
	private DepartmentService departmentService;
    
    @GetMapping("/departments")
    public Object getDepartmentsPage(HttpSession session) {
        ModelAndView view = new ModelAndView("superAdminPages/setupDepartments");
        try{
        	User currentUser = (User) session.getAttribute(ApplicationConstants.LoggedUser);
        	if(currentUser == null) {
        		session.invalidate();
        		return "redirect:/showLogin";
        	}
        	view.addObject("department",new Departments());
        	view.addObject("departments", departmentService.getDepatmentsByUserId(currentUser.getId()));
        }catch(Exception e){
            e.printStackTrace();
        }
        return view;
    }
    
    @PostMapping("/createDepartment")
    public Object createDepartment(@ModelAttribute("department") Departments department,HttpSession session,HttpServletRequest request, RedirectAttributes attributes) {
        try {
        	User currentUser = (User) session.getAttribute(ApplicationConstants.LoggedUser);
        	if(currentUser == null) {
        		session.invalidate();
        		return "redirect:/showLogin";
        	}
        	Departments newDepartment = departmentService.getDepatmentByUserIdAndName(department.getUserId(), department.getDepartmentName());
        	if(newDepartment != null) {
        		attributes.addFlashAttribute("msg","Department already exists.");
        	}else {
        		departmentService.saveDepartment(department);
        		attributes.addFlashAttribute("msg","Department created successfully.");
        	}
        }catch (Exception e) {
			e.printStackTrace();
		}
        return "redirect:/departments";
    }
    
    
}
