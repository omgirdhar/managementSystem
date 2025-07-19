package com.example.demo.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.PasswordUtil;
import com.example.demo.constants.ApplicationConstants;
import com.example.demo.constants.EnumConstants.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        createSuperAdmin();
        User user = userService.getUserByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User with username "+username + " not found.");
        }
        httpSession.setAttribute(ApplicationConstants.LoggedUser, user);
        return new CustomUserDetails(user);
    }

    private void createSuperAdmin(){
        List<User> users = userService.getAllUsers();
        if(users == null || users.isEmpty()){
            User user = new User();
            user.setEmail("superadmin.emp@gmail.com");
            user.setFirstName("Super");
            user.setLastName("Admin");
            user.setRole(Role.SUPERADMIN);
            user.setPassword(PasswordUtil.encodePassword("Super@Emp$130"));
            userService.saveUser(user);
        }
    }
    

}
