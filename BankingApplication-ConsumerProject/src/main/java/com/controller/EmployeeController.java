package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Employee;
import com.beans.User;
import com.model.service.EmployeeService;

@SessionAttributes({"employee"})
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	
	@RequestMapping("/employeeLoginPage")
	public ModelAndView employeePage()
	{
		return new ModelAndView("employeeLogin");
	}
	
	@RequestMapping("/employeeLogin")
	public ModelAndView employeelogin(@RequestParam(value = "email") String email,@RequestParam(value="password") String password) {
		System.out.println(email+" "+password);
		Employee employee=employeeService.validateEmployee(email, password);
		if(employee!=null) {
			return new ModelAndView("employeedashboard");
		}
		else {
			return new ModelAndView("employeeLogin","message","Please enter valid details");
		}
	}
	@RequestMapping("/employeeUserPage")
	public ModelAndView employeeUserPage()
	{
		return new ModelAndView("employeeUserInput");
	}

	
	@RequestMapping("/employeeUserInput")
	public ModelAndView getEmployeeByAccount(long accNo) {
		User user=employeeService.getDetailsOfUserByAccountNo(accNo);
		if(user!=null) {
			return new ModelAndView("employeeUserDashboard");
		}
		else {
			return new ModelAndView("employeeUserInput","message","Please Enter Valid Account Number");
		}
	}
	
	@RequestMapping("/employeeUserFunctionsPage")
	public ModelAndView employeeFunctionsPage()
	{
		return new ModelAndView("employeeUserDashboard");
	}

}

