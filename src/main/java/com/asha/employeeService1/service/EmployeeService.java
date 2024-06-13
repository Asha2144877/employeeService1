package com.asha.employeeService1.service;

import java.util.List;


public interface EmployeeService<Employee> {
	
	public void saveEmployee(Employee employee);
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(Long id);
	public void deleteEmployeeById(Long id);
	//public Employee getEmployeeByCode(String employeeCode);
	
}
