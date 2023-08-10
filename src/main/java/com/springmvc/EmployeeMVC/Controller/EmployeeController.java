package com.springmvc.EmployeeMVC.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springmvc.EmployeeMVC.Entity.Employee;
import com.springmvc.EmployeeMVC.Services.EmployeeServiceImp;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeServiceImp employeeService;

	@Autowired
	public EmployeeController(EmployeeServiceImp employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/list")
	public String getEmployees(Model theModel) {
		
		List<Employee> employees = employeeService.findAll();
		
		theModel.addAttribute("employees", employees);
		
		return "employee/list";
		
	}
	
	@GetMapping("/showAddForm")
	public String showAddForm(Model theModel) {
		
		Employee emp = new Employee();
		
		theModel.addAttribute("employee", emp);
		
		return "employee/registration-form";
		
	}
	
	@PostMapping("/save")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee emp, 
			BindingResult bindingResult) {
		
		if(okayToUpdate(emp,bindingResult)) {
			System.out.println("Just a check........");
		}
		
		if(bindingResult.hasErrors()) {
			return "employee/registration-form";
		} else {
			employeeService.save(emp);
		}
		
		return "redirect:/employees/list";
	}
	
	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam("employeeId") int id, Model theModel) {
		
		Employee emp = employeeService.findById(id);
		
		theModel.addAttribute("employee",emp);
		
		return "employee/registration-form";
		
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
	}
	
	public Boolean okayToUpdate(Employee emp, BindingResult br) {
		/** This is helper method
		 * @UniqueEmail will check whether the email is already present in DataBase or not
		 * But in case of update, if user do not update email than the email will be present in 
		 * Database and we will get @UniqueEmail error
		 * But this is perfectly fine case.
		 * To overcome this, we will use this method
		 * If we get error from @UniqueEmail than we will further check that the failed email is 
		 * same as the email we get by employee id (this will happen in case of update) if this
		 * is case than it is okay to save/update employee in database
		 */
		
		int errorCount = br.getFieldErrors().size();
		
		if(emp.getId() > 0 && errorCount == 1) {
			
			String empEmail = emp.getEmail();
			String errorEmail = (String) br.getFieldError("email").getRejectedValue();
	
			if(empEmail.equals(errorEmail)) {
				return true;
			}
		}
		
		return false;
		
	}
	
}
