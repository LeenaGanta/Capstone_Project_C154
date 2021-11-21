package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.beans.TransactionList;
import com.beans.TransferList;
import com.beans.User;
import com.model.service.TransactionService;
import com.model.service.TransferService;
import com.model.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TransferService transferService;
	
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping("/newUser")
	public ModelAndView showLoginPage() {
		return new ModelAndView("UserRegistration","user",new User());
//		"command",new User()
	}
	
	@RequestMapping("/UserRegister")
	public ModelAndView registerUserController(@ModelAttribute  User user) {
		System.out.println(user);
		User user1=userService.registerUser(user);
		return new ModelAndView("UserRegistration","user",user1);
	}
	
	@RequestMapping("/updateDetails")
	public ModelAndView UpdateUserController(HttpSession session) {
		User user=(User) session.getAttribute("user");
		System.out.println(user);
		User user1=userService.updateUser(user);
		return new ModelAndView("UserUpdation","user",user1);
	}
	
	@RequestMapping("/UserUpdateDb")
	public ModelAndView UpdateUserControllerDB(@ModelAttribute  User user,HttpSession session) {
		User user2=(User) session.getAttribute("user");
		user.setAccNo(user2.getAccNo());
		System.out.println(user);
		User user1=userService.updateUser(user);
		session.setAttribute("user", user);
		return new ModelAndView("UserUpdation","message","Details updated sucessfully");
	}
	
	@RequestMapping("/displayTransfers")
	public ModelAndView displayingTransfers(HttpSession session)
	{
		User user=(User) session.getAttribute("user");
		TransferList transferList=transferService.getByAccount(user.getAccNo());
		
		return new ModelAndView("TransfersDisplay","transferList",transferList.getTransfer());
	}
//	TransactionsDisplay
	@RequestMapping("/displayTransactions")
	public ModelAndView displayTransactions(HttpSession session)
	{
		User user=(User) session.getAttribute("user");
		TransactionList transactionList=transactionService.getAllTransactionsByAccNo(user.getAccNo());
		return new ModelAndView("TransactionsDisplay","transactionList",transactionList.getTransactions());
	}

}
