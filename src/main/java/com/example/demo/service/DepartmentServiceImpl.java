package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Departments;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public List<Departments> getDepatmentsByUserId(long id) {
		return departmentRepository.findByUserId(id);
	}
	
	@Override
	public Departments saveDepartment(Departments department) {
		return departmentRepository.save(department);
	}

	
	@Override
	public void deleteDepatmentByUserIdAndName(long id, String name) {
		departmentRepository.deleteByUserIdAndDepartmentName(id,name);
	}

	@Override
	public Departments getDepatmentByUserIdAndName(long id, String name) {
		return departmentRepository.findByUserIdAndDepartmentName(id, name);
	}

}
