package com.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Admin;
import com.beans.EmployeeList;
import com.beans.UserList;
import com.model.service.AdminService;

@RestController
public class AdminResource {
	
	@Autowired
	private AdminService adminService;
	
	
	//Working
	@GetMapping(path="/users")
	UserList getAllUsers()
	{
		return adminService.getAllUsers();
	}
	
	//Working
	@GetMapping(path="/employees")
	EmployeeList getAllEmployees()
	{
		return adminService.getAllEmployees();
	}
	
	//Working
	@GetMapping(path="/admin/{mailId}/{password}")
	Admin validateAdmin(@PathVariable("mailId") String mailId,@PathVariable("password") String password)
	{
		return adminService.validateAdmin(mailId, password);
	}

}
