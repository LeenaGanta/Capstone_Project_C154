package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Admin;
import com.beans.Employee;
import com.beans.EmployeeList;
import com.beans.UserList;
import com.model.service.AdminService;
import com.model.service.EmployeeService;

@SessionAttributes({"admin"})
@RestController
public class AdminController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AdminService adminService;
	
	Admin admin=null;
	
	@RequestMapping("/")
	public ModelAndView firstPage() {
		return new ModelAndView("welcome");
	}
	
	@RequestMapping("/adminLoginPage")
	public ModelAndView userPage()
	{
		return new ModelAndView("adminLogin");
	}
	
	
	@RequestMapping("/adminloginCheck")
	public ModelAndView adminloginPage(@RequestParam(value="email id") String mailId,@RequestParam(value="password") String password) {
		System.out.println(mailId+","+password);
		if(adminService.validateAdmin(mailId, password))
		{	return new ModelAndView("dashboard");}
		else
		{	return new ModelAndView("adminLoginLogin","message", "Please enter correct credentials");}
	}
	
	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		return new ModelAndView("dashboard");
	}
	
	@RequestMapping("/getAllEmp")
	public ModelAndView getAllEmployeesController() {
		EmployeeList employees=adminService.getAllEmployees();
		return new ModelAndView("showAllEmployees", "employees", employees);
	}
	
	@RequestMapping("/getAllUsers")
	public ModelAndView getAllUsersController() {
		UserList users=adminService.getAllUsers();
		return new ModelAndView("showAllEmployees", "employees", users);
	}

}