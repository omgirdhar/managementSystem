package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.EnumConstants.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findByRoleNot(Role.SUPERADMIN);
    }


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
}
