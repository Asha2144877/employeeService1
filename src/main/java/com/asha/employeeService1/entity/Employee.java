package com.asha.employeeService1.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String employeeCode;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNumber;
	private String designation;
	@ManyToOne
    @JoinColumn(name = "manager_id")
	@JsonIgnoreProperties("subordinates")
	
	private Employee manager;
	private String address;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="manager", orphanRemoval = true)
	@JsonIgnoreProperties("manager")
	List<Employee> subordinates = new ArrayList<>();
	private Integer subordinateCount;
	
	public Employee() {}
	
	
	


	public Employee(String employeeCode, String firstName, String lastName, String emailId, String phoneNumber,
			String designation, Employee manager, String address, List<Employee> subordinates,
			Integer subordinateCount) {
		super();
		this.employeeCode = employeeCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.designation = designation;
		this.manager = manager;
		this.address = address;
		this.subordinates = subordinates;
		this.subordinateCount = subordinateCount;
	}





	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmployeeCode() {
		return employeeCode;
	}


	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}


	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public List<Employee> getSubordinates() {
		return subordinates;
	}
	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
	}
	public Integer getSubordinateCount() {
		return subordinateCount;
	}
	public void setSubordinateCount(Integer subordinateCount) {
		this.subordinateCount = subordinateCount;
	}





	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeCode=" + employeeCode + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailId=" + emailId + ", phoneNumber=" + phoneNumber + ", designation=" + designation
				+ ", address=" + address + ", subordinateCount=" + subordinateCount + "]";
	}





	

	

	
	
	
}
