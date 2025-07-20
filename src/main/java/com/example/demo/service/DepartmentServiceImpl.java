package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}
	
	@Override
	public void deleteDepartment(Department department) {
		departmentRepository.delete(department);
	}

	@Override
	public Department getDepartmentByName(String name) {
		return departmentRepository.findByDepartmentName(name);
	}

	@Override
	public Department getDepartmentById(long id) {
		return departmentRepository.findById(id).orElse(null);
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

}
