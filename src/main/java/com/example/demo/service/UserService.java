package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

    List<User> getAllUsers();
    
    User getUserByEmail(String email);
    
    User getUserById(long id);

    User saveUser(User user);
}
