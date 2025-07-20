package com.example.demo.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.PasswordUtil;
import com.example.demo.constants.ApplicationConstants;
import com.example.demo.constants.EnumConstants;
import com.example.demo.model.User;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private DepartmentService departmentService;
	
    @GetMapping("/users")
    public Object getUsersPage(HttpSession session) {
        ModelAndView view = new ModelAndView("superAdminPages/setupUsers");
        try{
        	User currentUser = (User) session.getAttribute(ApplicationConstants.LoggedUser);
        	if(currentUser == null) {
        		session.invalidate();
        		return "redirect:/showLogin";
        	}
        	view.addObject("user", new User());
        	view.addObject("roles",EnumConstants.Role.getAssignableRoles());
        	view.addObject("allUsers",userService.getAllUsers());
        	view.addObject("departments",departmentService.getDepatmentsByUserId(currentUser.getId()));
        }catch(Exception e){
            e.printStackTrace();
        }
        return view;
    }
    
    @PostMapping("/createUser")
    private Object createUser(@ModelAttribute("user") User user, HttpSession session, RedirectAttributes attributes) {
    	try {
    		User currentUser = (User) session.getAttribute(ApplicationConstants.LoggedUser);
        	if(currentUser == null) {
        		session.invalidate();
        		return "redirect:/showLogin";
        	}
    		
        	User newUser = userService.getUserByEmail(user.getEmail());
        	if(newUser != null) {
        		attributes.addFlashAttribute("msg","User email already exist. Please try a different email.");
        	}else {
        		user.setPassword(PasswordUtil.encodePassword(user.getPassword()));
        		newUser = userService.saveUser(user);
        		attributes.addFlashAttribute("msg","User created successfully.");
        	}
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return "redirect:/users";
    }
    
    @GetMapping("/getUser/{id}")
    @ResponseBody
    private Object getUser(@PathVariable long id) {
    	try {
    		User user = userService.getUserById(id);
    		if(user != null) {
            	return ResponseEntity.ok(user);
    		}else {
    			return ResponseEntity.notFound();
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
			return ResponseEntity.badRequest();
		}
    }
}
