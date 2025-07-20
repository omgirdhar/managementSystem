package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.constants.EnumConstants.Role;
import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
    User findByEmail(String email);
    
    List<User> findByRoleNot(Role role);
}
