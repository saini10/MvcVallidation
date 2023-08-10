package com.springmvc.EmployeeMVC.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springmvc.EmployeeMVC.Entity.Employee;

public interface EmployeeDAO extends JpaRepository<Employee,Integer>{
	
	@Query(value = "SELECT count(email) FROM employeerepo.employee where employee.email=?1",
			nativeQuery=true)
	int emailCount(String email);

}
