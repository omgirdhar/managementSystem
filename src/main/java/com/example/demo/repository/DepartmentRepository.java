package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Departments;
import java.util.List;


public interface DepartmentRepository extends JpaRepository<Departments, Long>{

	List<Departments> findByUserId(long userId);
	
	Departments findByUserIdAndDepartmentName(long userId, String departmentName);
	
	void deleteByUserIdAndDepartmentName(long userId, String departmentName);
}
