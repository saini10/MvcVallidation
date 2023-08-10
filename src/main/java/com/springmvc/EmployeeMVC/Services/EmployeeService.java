package com.springmvc.EmployeeMVC.Services;

import java.util.List;

import com.springmvc.EmployeeMVC.Entity.Employee;

public interface EmployeeService {
	
	List<Employee> findAll();
	
	Employee findById(int id);
	
	Employee save(Employee emp);
	
	Employee update(Employee emp);
	
	void deleteById(int id);
	
	int emailCount(String email);

}
