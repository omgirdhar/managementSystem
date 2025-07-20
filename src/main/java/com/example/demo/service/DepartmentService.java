package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Department;

public interface DepartmentService {
	
	List<Department> getAllDepartments();
	
	Department getDepartmentByName(String name);
	
	Department getDepartmentById(long id);
	
	Department saveDepartment(Department department);
	
	void deleteDepartment(Department department);
}
