package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EmpUser;

public interface EmpUserService {

    List<EmpUser> findAllUsers();
    
    EmpUser getUserByEmail(String email);

    EmpUser saveUser(EmpUser user);
}
