package com.snehal.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.snehal.demo.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Value("${erm.rest.url}")
	private String url;

	@Autowired
	RestTemplate restTemplate;
	
	
	@Override
	public List<Employee> getAllEmployees() {
		ResponseEntity<List<Employee>> responseEntity =restTemplate.exchange(url,HttpMethod.GET, null,new ParameterizedTypeReference<List<Employee>>() {}); 
		   
		List<Employee> employees = responseEntity.getBody();
		
		return employees;
		
	}

	@Override
	public Employee getEmployee(int id) {
		
		Employee e =restTemplate.getForObject(url+"/"+id,Employee.class);
	      return e;
		
	}

	@Override
	public Employee save(Employee e) {
		
		ResponseEntity<Employee> emp=restTemplate.postForEntity(url,e,Employee.class);
		return emp.getBody();
	
	}

	@Override
	public void delete(int id) {
		restTemplate.delete(url+"/"+id);
	}

	@Override
	public void update(Employee e) {
		restTemplate.put(url,e);
	}

	
	

}
