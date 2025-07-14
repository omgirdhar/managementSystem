package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmpUser;
import com.example.demo.repository.EmpUserRepository;

@Service
public class EmpUserServiceImpl implements EmpUserService{

    @Autowired
    private EmpUserRepository userRepository;


    @Override
    public EmpUser getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public List<EmpUser> findAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public EmpUser saveUser(EmpUser user) {
        return userRepository.save(user);
    }
    
}
