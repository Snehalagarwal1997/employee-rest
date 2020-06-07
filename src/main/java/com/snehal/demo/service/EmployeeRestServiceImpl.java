package com.snehal.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snehal.demo.Repository.EmployeeRepository;
import com.snehal.demo.model.Employee;

@Service
public class EmployeeRestServiceImpl implements EmployeeRestService {

	
	@Autowired
	EmployeeRepository repo;
	
	@Override
	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}

	@Override
	public Employee getEmployee(int id) {
		Employee e =repo.findById(id);
		return e;
	}

	@Override
	public Employee saveEmployee(Employee e) {
		e.setId(0);
		Employee emp=repo.save(e);
		return emp;
	}

	@Override
	public Employee updateEmployee(Employee e) {
		Employee emp=repo.save(e);
		return emp;
	}

	@Override
	public void deleteEmployee(int id) {
		Employee e =repo.findById(id);
		repo.delete(e);
		
	}

}
