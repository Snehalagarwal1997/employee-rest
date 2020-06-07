package com.snehal.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snehal.demo.model.Employee;
import com.snehal.demo.service.EmployeeService;

@Controller
@PropertySource("classpath:application.properties")
@RequestMapping("/employee")
public class EmployeeController {
	
	@Value("${erm.rest.url}")
	private String url;

	private EmployeeService service;
	
	
	public EmployeeController() {}
	
	@Autowired
	public EmployeeController(EmployeeService service)
	{
		this.service=service;
	}
	
	@InitBinder
	public void trim(WebDataBinder wdb)
	{
		StringTrimmerEditor ste=new StringTrimmerEditor(true);
		wdb.registerCustomEditor(String.class, ste);
	}
	
	@GetMapping("/list")
	public String getAllEmployees(Model m)
	{
		List<Employee> e=service.getAllEmployees();
		m.addAttribute("allEmployees",e);
		return "home";
	}
	
	@GetMapping("/showAdd")
	public String showAdd(Model m)
	{
		Employee e=new Employee();
		m.addAttribute("employee",e);
		return "add";
	}
	
	
	@PostMapping("/add")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee e,BindingResult br)
	{
		if(br.hasErrors())
			return "add";
		else {
		Employee emp=service.save(e);
		return "redirect:/employee/list";
		}
	}
	
	@GetMapping("/edit/{id}")
	public String showEdit(@PathVariable("id")int id,Model m) {
		Employee e=service.getEmployee(id);
		m.addAttribute(e);
		return "update";
	}
	
	@PostMapping("/update/{id}")
	public String updateEmployee(@Valid @ModelAttribute("employee") Employee e,BindingResult br)
	{
		if(br.hasErrors())
			return "update";
		else {
		service.update(e);
		return "redirect:/employee/list";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id")int id)
	{
		service.delete(id);
		return "redirect:/employee/list";
	}
	
	@GetMapping("/confirmDelete/{id}")
	public String delete(@PathVariable("id")int id,Model m) {
		Employee e=service.getEmployee(id);
		m.addAttribute(e);
		return "delete";
		
	}
		
		
}
