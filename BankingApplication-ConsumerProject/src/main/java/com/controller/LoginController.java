package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.model.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public ModelAndView firstPage() {
		return new ModelAndView("welcome");
	}
	
	@RequestMapping("/userLoginPage")
	public ModelAndView userPage()
	{
		return new ModelAndView("userLogin");
	}
	
	@RequestMapping("/userloginCheck")
	public ModelAndView userloginPage(@RequestParam(value="accNo") Long accNo,@RequestParam(value="password") String password) {
		System.out.println(accNo+","+password);
		if(userService.validateUser(accNo, password))
		{	return new ModelAndView("dashboard");}
		else
		{	return new ModelAndView("userLogin","message", "Please enter correct credentials");}
	}

}
