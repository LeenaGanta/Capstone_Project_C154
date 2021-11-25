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
import com.model.service.BalanceService;
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
	
	@Autowired
	BalanceService balanceService;

	@RequestMapping("/newUser")
	public ModelAndView showLoginPage() {
		return new ModelAndView("UserRegistration", "user", new User());
//		"command",new User()
	}

	@RequestMapping("/UserRegister")
	public ModelAndView registerUserController(@ModelAttribute User user) {
		System.out.println(user);
		User user1 = userService.registerUser(user);
		if (user1 != null)
			return new ModelAndView("UserRegistration", "message", "Your account number is-> " + user1.getAccNo());

		return new ModelAndView("UserRegistration", "message", "Sorry registration failed Try again");
	}

	@RequestMapping("/updateDetails")
	public ModelAndView UpdateUserController(HttpSession session) {
		User user1 = (User) session.getAttribute("user");
		System.out.println(user1);
//		User user1 = userService.updateUser(user);
		return new ModelAndView("UserUpdation", "user", user1);
	}

	@RequestMapping("/UserUpdateDb")
	public ModelAndView UpdateUserControllerDB(@ModelAttribute User user, HttpSession session) {
		User user2 = (User) session.getAttribute("user");
//		if (!user2.getPassword().equals(user.getPassword()))
//			return new ModelAndView("UserUpdation", "message", "Password Didnt Match");
		user.setAccNo(user2.getAccNo());
		System.out.println(user);
		User user1 = userService.updateUser(user);
		if(user1==null)
		{
			return new ModelAndView("UserUpdation", "message", "Password Didnt Match");
		}
		session.setAttribute("user", user1);
		return new ModelAndView("UserUpdation", "message", "Details updated sucessfully");
	}

	@RequestMapping("/displayTransfers")
	public ModelAndView displayingTransfers(HttpSession session) {
		User user = (User) session.getAttribute("user");
		TransferList transferList = transferService.getByAccount(user.getAccNo());
		if (transferList.getTransfer().size() == 0)
			return new ModelAndView("TransfersDisplay", "message", "No Transfers to Display");

		return new ModelAndView("TransfersDisplay", "transferList", transferList.getTransfer());
	}

//	TransactionsDisplay
	@RequestMapping("/displayTransactions")
	public ModelAndView displayTransactions(HttpSession session) {
		User user = (User) session.getAttribute("user");
		TransactionList transactionList = transactionService.getAllTransactionsByAccNo(user.getAccNo());

		if (transactionList.getTransactions().size() == 0)
			return new ModelAndView("TransactionsDisplay", "message", "No transactions To display");
		return new ModelAndView("TransactionsDisplay", "transactionList", transactionList.getTransactions());
	}
	
	@RequestMapping("/userDetailsDisplay")
	public ModelAndView displayDetails(HttpSession session)
	{
		User user1=(User)session.getAttribute("user");
		double balance=balanceService.getBalance(user1.getAccNo());
		ModelAndView mv=new ModelAndView();
		mv.setViewName("usersDetailsDisplay");
		mv.addObject("user", user1);
		mv.addObject("balance",balance);
		return mv;
	}

}
