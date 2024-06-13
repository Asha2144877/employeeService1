package com.asha.employeeService1.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asha.employeeService1.entity.Employee;
import com.asha.employeeService1.service.EmployeeService;



@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService<Employee> employeeService;
	
	
	@GetMapping(value = "/getAllEmployees")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	
	@GetMapping(value= "/getEmployeeById/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		
		return employeeService.getEmployeeById(id);
	}
	
	@GetMapping(value = "/getAllManagers")
	public List<Employee> getAllManagers(){
		List<Employee> allEmployees = employeeService.getAllEmployees();
		// first get all employees then check subordinate count is 0
		List<Employee> allManagers = allEmployees.stream().filter(n ->n.getSubordinateCount() != 0 ).collect(Collectors.toList());
		// first get all employees then check subordinate count is 0
		
		return allManagers;
	}

}
