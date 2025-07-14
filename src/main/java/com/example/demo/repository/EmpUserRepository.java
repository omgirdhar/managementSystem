package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.EmpUser;

public interface EmpUserRepository extends JpaRepository<EmpUser, Long>{
    
    EmpUser findByEmail(String email);
}
