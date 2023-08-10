package com.springmvc.EmployeeMVC.Vallidation;

import org.springframework.beans.factory.annotation.Autowired;

import com.springmvc.EmployeeMVC.Services.EmployeeService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailConstraintValidator implements ConstraintValidator<UniqueEmail, String> {

	private EmployeeService empService;

	public UniqueEmailConstraintValidator() {

	}

	@Autowired
	public UniqueEmailConstraintValidator(EmployeeService empService) {
		this.empService = empService;
	}

	@Override
	public void initialize(UniqueEmail email) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (empService.emailCount(value) == 0) {
			return true;
		}

		return false;
	}

}
