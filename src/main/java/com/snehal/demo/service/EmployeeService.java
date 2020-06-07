package com.snehal.demo.service;


import java.util.List;



import com.snehal.demo.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployee(int id);
	public Employee save(Employee e);
	public void update(Employee e);
	public void delete(int id);
	
	
}
