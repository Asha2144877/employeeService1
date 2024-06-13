package com.asha.employeeService1.service;

import java.util.List;
import java.util.stream.Collectors;

//import com.asha.employeeService.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl<Employee> implements EmployeeService<Employee> {

	@Autowired
	private SimpleJpaRepository<Employee,Long> employeeSimpleRepository;
	
//	@Autowired
//	private EmployeeRepository<Employee,Long> employeeRepository;
	@Override
	public void saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeSimpleRepository.save(employee);
	}
	@Override
	public List<Employee> getAllEmployees(){
		return employeeSimpleRepository.findAll(Sort.by("employeeCode").ascending());
	//	return employeeSimpleRepository.findAll();
	}

	
	public Employee getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		return employeeSimpleRepository.findById(id).isPresent()? employeeSimpleRepository.findById(id).get(): null;
	}
	@Override
	public void deleteEmployeeById(Long id) {
		// TODO Auto-generated method stub
		employeeSimpleRepository.deleteById(id);
	}
//	@Override
//	public Employee getEmployeeByCode(String employeeCode) {
//		// TODO Auto-generated method stub
//		List<Employee> allEmployees = getAllEmployees();
//		Employee employeeByCode = null;
//		for(Employee employee : allEmployees) {
//			if(((Employee)employee))
//		}
//		
//		for(int i =0;i<allEmployees.size();i++) {
//			if(allEmployees.get(i).)
//		}
//		
//		employeeRepository.find
//		return employeeByCode;
//	}
//	

}
