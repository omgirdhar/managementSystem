package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Department;
import java.util.List;


public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	Department findByDepartmentName(String departmentName);
	
}
