package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping("/adminLoginPage")
	public ModelAndView userPage()
	{
		return new ModelAndView("adminLogin");
	}
	
	
	@RequestMapping("/adminloginCheck")
	public ModelAndView adminloginPage(@RequestParam(value="mailId") String mailId,@RequestParam(value="password") String password) {
		System.out.println(mailId+","+password);
		if(adminService.validateAdmin(mailId, password))
		{	return new ModelAndView("adminDashboard");}
		else
		{	return new ModelAndView("adminLogin","message", "Please enter correct credentials");}
	}
	
	@RequestMapping("/adminDashboard")
	public ModelAndView adminDashboardController() {
		return new ModelAndView("adminDashboard");
	}
	
	@RequestMapping("/getAllEmp")
	public ModelAndView getAllEmployeesController() {
		EmployeeList employees=adminService.getAllEmployees();
		return new ModelAndView("showAllEmployees", "employees", employees.getEmployees());
	}
	
	@RequestMapping("/getAllUsers")
	public ModelAndView getAllUsersController() {
		UserList users=adminService.getAllUsers();
		return new ModelAndView("showAllUsers", "users", users.getUsers());
	}
	
	@RequestMapping("/addEmployee")
	public ModelAndView addEmployee()
	{
		return new ModelAndView("employeeRegistration","employee",new Employee());
	}
	
	@RequestMapping("/employeeRegister")
	public ModelAndView employeeAddDB(@ModelAttribute("employee") Employee employee)
	{
		Employee employee1=adminService.addEmployee(employee);
		return new ModelAndView("employeeRegistration","message","Employee registered sucessfully");
	}
	
	@RequestMapping("/adminLogout")
	public ModelAndView logoutSystem(HttpServletRequest request)
	{
		  HttpSession httpSession = request.getSession();
          httpSession.invalidate();
	   	  return new ModelAndView("welcome");
	}

}