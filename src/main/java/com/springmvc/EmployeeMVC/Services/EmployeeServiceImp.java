package com.springmvc.EmployeeMVC.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.EmployeeMVC.DAO.EmployeeDAO;
import com.springmvc.EmployeeMVC.Entity.Employee;

@Service
public class EmployeeServiceImp implements EmployeeService {

	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeServiceImp(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	@Override
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Override
	public Employee findById(int id) {
		Optional<Employee> employee = employeeDAO.findById(id);
		
		if(employee.isPresent()) {
			return employee.get();
		} else {
			return null;
		}
		
	}

	@Override
	public Employee update(Employee emp) {
		
		if(findById(emp.getId()) != null) {
			return employeeDAO.save(emp);
		} else {
			throw new RuntimeException("Student does not exist");
		}
		
	}

	@Override
	public void deleteById(int id) {

		employeeDAO.deleteById(id);
		
	}

	@Override
	public Employee save(Employee emp) {
		return employeeDAO.save(emp);
	}

	@Override
	public int emailCount(String email) {
		return employeeDAO.emailCount(email);
	}

}
