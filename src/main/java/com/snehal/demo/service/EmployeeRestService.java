package com.snehal.demo.service;


import java.util.List;



import com.snehal.demo.model.Employee;

public interface EmployeeRestService {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployee(int id);
	public Employee saveEmployee(Employee e);
	public Employee updateEmployee(Employee e);
	public void deleteEmployee(int id);
	
}
