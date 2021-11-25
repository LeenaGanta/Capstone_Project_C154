package com.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Admin;
import com.beans.Employee;
import com.beans.EmployeeList;
import com.beans.User;
import com.beans.UserList;
import com.exceptions.AccountNotFoundException;
import com.model.service.AdminService;

@RestController
public class AdminResource {
	
	@Autowired
	private AdminService adminService;
	
	
	//Working
	@GetMapping(path="/users")
	ResponseEntity<UserList> getAllUsers()
	{
		UserList users=adminService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	//Working
	@GetMapping(path="/employees")
	ResponseEntity<EmployeeList> getAllEmployees()
	{
		EmployeeList employees=adminService.getAllEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(employees);
	}
	
	//Working
	@GetMapping(path="/admin/{mailId}/{password}")
	ResponseEntity<Admin> validateAdmin(@PathVariable("mailId") String mailId,@PathVariable("password") String password)
	{
		Admin admin=adminService.validateAdmin(mailId, password);
		if(admin!=null)
		return ResponseEntity.status(HttpStatus.OK).body(admin);
		else
			throw new AccountNotFoundException("Please Check for the correct credentials");
	}
	
	@PostMapping(path="/admin/add/{employee}")
	public Employee registerUser(@RequestBody Employee employee)
	{
		return adminService.addEmployee(employee);
	}
}
