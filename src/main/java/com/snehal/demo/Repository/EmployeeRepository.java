package com.snehal.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snehal.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	
	public Employee findById(int id);

}
