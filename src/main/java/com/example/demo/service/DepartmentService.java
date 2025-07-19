package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Departments;

public interface DepartmentService {

	List<Departments> getDepatmentsByUserId(long id);
	
	Departments getDepatmentByUserIdAndName(long id, String name);
	
	Departments saveDepartment(Departments department);
	
	void deleteDepatmentByUserIdAndName(long id, String name);
}
