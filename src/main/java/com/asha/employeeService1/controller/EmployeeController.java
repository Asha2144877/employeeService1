package com.asha.employeeService1.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asha.employeeService1.entity.Employee;
import com.asha.employeeService1.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;




@Controller
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService<Employee> employeeService ;
	
	@GetMapping(value = "/hierarchyLandingPage")
	public ModelAndView getLandingPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hierarchyLandingPage");
		return mv;
	}
	
	@PostMapping(value="/addEmployee")
	public ModelAndView addEmployee() 
	{
		ModelAndView mv = new ModelAndView();
		List<Employee> allEmployees = employeeService.getAllEmployees();
		mv.addObject("allEmployees",allEmployees);
		mv.setViewName("addEmployee");
		return mv;
	}
	
	@PostMapping(value="/submitEdittedEmployee")
	public ModelAndView updateEmployee(HttpSession session,@RequestParam(required=false) String firstName,
			@RequestParam(required=false) String lastName, @RequestParam(required=false) String phoneNumber,@RequestParam(required=false) String designation,
			
			@RequestParam(required=false) String email, @RequestParam(required=false) String address, @RequestParam(required=false)  String employeeCode, @RequestParam(defaultValue = "0") Long selectedmanager, Model model) 
	{
		// get information about employee to edit
		
		Long employeeIdToEdit = (Long) session.getAttribute("employeeIdToEdit");
		Employee employeeToEdit = employeeService.getEmployeeById(employeeIdToEdit);
		
		// update the newly entered properties
		
		employeeToEdit.setFirstName(firstName);
		employeeToEdit.setLastName(lastName);
		employeeToEdit.setAddress(address);
		employeeToEdit.setEmployeeCode(employeeCode);
		employeeToEdit.setEmailId(email);
		employeeToEdit.setPhoneNumber(phoneNumber);
		employeeToEdit.setDesignation(designation);
		
		// obtain current and new manager ids
		
		Long savedManagerId = employeeToEdit.getManager() == null? null : employeeToEdit.getManager().getId();
		Long newManagerId = selectedmanager;
		
		
		// if manager selection changes - 
		
		if(savedManagerId !=newManagerId ) {
			
			//add current employee as subordinate of new manager, increase the subordinate count of new manager and save the new manager
			
			Employee newManagerObject = employeeService.getEmployeeById(newManagerId);
			employeeToEdit.setManager(newManagerObject);
			if(newManagerObject != null) {
				newManagerObject.getSubordinates().add(employeeToEdit);
				newManagerObject.setSubordinateCount(newManagerObject.getSubordinateCount() + 1);
				employeeService.saveEmployee(newManagerObject);
			}
			
			// if the old manager was valid, remove the current employee from the subordinate list and decrease the subordinate count
			if(savedManagerId != null) {
				Employee savedManagerObject= employeeService.getEmployeeById(savedManagerId);
				if(savedManagerObject != null) {
					List<Employee> newSavedSubordinateList = savedManagerObject.getSubordinates().stream().filter(n-> ! n.getEmployeeCode().equals(employeeToEdit.getEmployeeCode())).collect(Collectors.toList());
					savedManagerObject.getSubordinates().clear();
					for(Employee subordinate : newSavedSubordinateList) {
						savedManagerObject.getSubordinates().add(subordinate);
					}
					savedManagerObject.setSubordinateCount(savedManagerObject.getSubordinateCount()-1);
					employeeService.saveEmployee(savedManagerObject);
				}	
			}
					
		}
		employeeService.saveEmployee(employeeToEdit);
		ModelAndView mv = new ModelAndView();
		
		// get employees who have manager set as "Not Applicable"
		List<Employee> allEmployees = employeeService.getAllEmployees();
		List<Employee> topLevelManagers = allEmployees.stream().
				filter(n ->  n.getManager() == null).
				collect(Collectors.toList()); 
		System.out.println("*********************** within show managers");
		System.out.println(topLevelManagers);
		mv.addObject("allTopLevelManagers", topLevelManagers);
		mv.setViewName("showTopLevelManagers");
		return mv;
	}
	
	@PostMapping(value="/submitEmployee")
	public ModelAndView addEmployee(HttpSession session,@RequestParam(required=false) String firstName,
			@RequestParam(required=false) String lastName, 
			@RequestParam(required=false) String email, @RequestParam(required=false) String address, @RequestParam(required=false)  String employeeCode, @RequestParam(defaultValue = "0") Long selectedmanager, @RequestParam(required=false) String phoneNumber, @RequestParam(required=false) String designation, Model model) 
	{
		// create new Employee object and populate with user filled in data
		Employee subordinate = new Employee(); 
		
		subordinate.setFirstName(firstName);
		subordinate.setLastName(lastName);
		subordinate.setAddress(address);
		subordinate.setEmployeeCode(employeeCode);
		subordinate.setEmailId(email);
		subordinate.setPhoneNumber(phoneNumber);
		subordinate.setDesignation(designation);
		
		// initially  there are no subordinates
		subordinate.setSubordinateCount(0);
		
		// set the selected manager
		Employee manager = employeeService.getEmployeeById(selectedmanager);
		subordinate.setManager(manager);
		
		//update manager to add to subordinates and increase subordinate count of the manager
		
		if(manager != null) {
			manager.getSubordinates().add(subordinate);
			manager.setSubordinateCount(manager.getSubordinateCount()+1);
		}
		
		employeeService.saveEmployee(subordinate);
		
		ModelAndView mv = new ModelAndView();
		
		List<Employee> allEmployees = employeeService.getAllEmployees();
		mv.addObject("allEmployees",allEmployees);
		mv.setViewName("manageHierarchy");
		return mv;
	}
	
	@GetMapping(value = "/showManagers")
	public ModelAndView showManagers() {
		ModelAndView mv = new ModelAndView();
		
		// get only those employees whose manager is null
		
		List<Employee> allEmployees = employeeService.getAllEmployees();
		List<Employee> topLevelManagers = allEmployees.stream().
				filter(n -> n.getManager() == null).
				collect(Collectors.toList()); 
		
		mv.addObject("allTopLevelManagers", topLevelManagers);
		mv.setViewName("showTopLevelManagers");
		return mv;
	}
	
	@GetMapping(value= "/showSubordinates")
	public ModelAndView showSubordinates(@RequestParam Long id) {
		ModelAndView mv = new ModelAndView();
		List<Employee> subordinates = employeeService.getEmployeeById(id).getSubordinates();
		mv.addObject("subordinates", subordinates);
		mv.setViewName("showSubordinates");
		return mv;
	}
	
	@GetMapping(value= "/editEmployee")
	public ModelAndView editEmployee(HttpSession session,@RequestParam Long id) {
		ModelAndView mv = new ModelAndView();
		Employee employee = employeeService.getEmployeeById(id);
		
		session.setAttribute("employeeIdToEdit", id);
		
		// this list is needed to set the new manager
		
		List<Employee> allEmployees = employeeService.getAllEmployees();
		mv.addObject("employeeToEdit", employee);
		
		mv.addObject("allEmployees",allEmployees);
		mv.setViewName("editEmployee");
		return mv;
	}
	
	@GetMapping(value= "/deleteEmployee")
	public ModelAndView deleteEmployee(HttpSession session,@RequestParam Long id) {
		ModelAndView mv = new ModelAndView();
		 employeeService.deleteEmployeeById(id);
		 
		 
		 List<Employee> allEmployees = employeeService.getAllEmployees();
			List<Employee> topLevelManagers = allEmployees.stream().
					filter(n -> n.getManager() == null).
					collect(Collectors.toList()); 
			
			mv.addObject("allTopLevelManagers", topLevelManagers);
			mv.setViewName("showTopLevelManagers");
		
		return mv;
	}
	@PostMapping(value = "addEmployeeFromFile")	
	public void addEmployeesFromFile() {
		
	}
	
	@GetMapping(value="/backToHierarchyLandingPage")
	public ModelAndView getLandingPage(
			Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hierarchyLandingPage");
		return mv;
		
	}
	
	
	
	
	
}
