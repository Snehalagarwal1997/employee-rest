package com.snehal.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snehal.demo.model.Employee;
import com.snehal.demo.service.EmployeeRestService;
import com.snehal.demo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeRestService service;
	
	public EmployeeRestController() {}
	
	@Autowired
	public EmployeeRestController(EmployeeRestService service)
	{
		this.service=service;
	}
	
	@GetMapping("/employee")
	public List<Employee> getAllEmployees(){
		
		return service.getAllEmployees();
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable("id") int id){

		return service.getEmployee(id);
	}
	
	@PostMapping("/employee")
	public Employee saveEmployee(@RequestBody Employee e){
	
		return service.saveEmployee(e);
	}
	
	@PutMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee e)
	{
		return service.updateEmployee(e);
	}
	
	@DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") int id){
		
		
		Employee e=service.getEmployee(id);
		if(e==null)
			throw new RuntimeException("No employee with the id:"+id);
		service.deleteEmployee(id);
		return "Deleted employee with id :"+id;
	}
		
		
		
}
