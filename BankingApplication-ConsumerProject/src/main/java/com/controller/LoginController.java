package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.beans.TransactionList;
import com.beans.TransferList;
import com.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.model.service.BalanceService;
import com.model.service.TransactionService;
import com.model.service.TransferService;
import com.model.service.UserService;

@SessionAttributes({"user"})
@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BalanceService balanceService;
	@Autowired
	private TransferService transferService;
	@Autowired
	private TransactionService transactionService;
	User user=null;
	
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
	public ModelAndView userloginPage(@RequestParam(value="accNo") Long accNo,@RequestParam(value="password") String password,HttpSession session) {
		System.out.println(accNo+","+password);
		if(userService.validateUser(accNo, password)!=null)
		{
			this.user=userService.validateUser(accNo, password);
			session.setAttribute("user", this.user);
			return new ModelAndView("dashboard");}
		else
		{	return new ModelAndView("userLogin","message", "Please enter correct credentials");}
	}
	
	@RequestMapping("/depositPage")
	public ModelAndView depositPage()
	{
		return new ModelAndView("deposit");
	}
	
	@RequestMapping("/checkBalancePage")
	public ModelAndView checkBalance()
	{
		ModelAndView mv=new ModelAndView();
		Double balance=balanceService.getBalance(user.getAccNo());
		mv.addObject("balancemsg","Your balance is ");
		mv.addObject("balance",balance);
		mv.setViewName("balancePage");
		return mv;
	}

	@RequestMapping("/deposit")
	public ModelAndView depositPage(@RequestParam(value="amount") Double amount) {
		ModelAndView mv=new ModelAndView();
		if(amount<0)
		{
		mv.addObject("message", "Please enter a positive number");
		mv.setViewName("deposit");
		return mv;
		}
		if(balanceService.depositBalance(user.getAccNo(), amount))
		{	
			Double balance=balanceService.getBalance(user.getAccNo());
			mv.addObject("balancemsg","Your balance is ");
			mv.addObject("balance",balance);
			mv.addObject("message", "Amount Deposited");
			mv.setViewName("deposit");
			return mv;}
		else
		{
			mv.addObject("message", "Unable to deposit please try later");
			mv.setViewName("deposit");
			return mv;}
	}
	
	@RequestMapping("/withdrawPage")
	public ModelAndView withdrawPage()
	{
		return new ModelAndView("withdraw");
	}
	
	@RequestMapping("/withdraw")
	public ModelAndView withdrawPage(@RequestParam(value="amount") Double amount) {
		ModelAndView mv=new ModelAndView();
		if(amount<0)
		{
		mv.addObject("message", "Please enter a positive number");
		mv.setViewName("withdraw");
		return mv;
		}
		if(balanceService.withdrawBalance(user.getAccNo(), amount))
		{
			Double balance=balanceService.getBalance(user.getAccNo());
			mv.addObject("balancemsg","Your balance is ");
			mv.addObject("balance",balance);
			mv.addObject("message", "Amount Withdrawn");
			mv.setViewName("withdraw");
			return mv;
		}
		else
		{
			mv.addObject("message", "Amount exceeded your balance");
			mv.setViewName("withdraw");
			return mv;

		}
	}
	
	@RequestMapping("/transferPage")
	public ModelAndView transferPage()
	{
		return new ModelAndView("transfer");
	}
	
	@RequestMapping("/transfer")
	public ModelAndView transferPage(@RequestParam(value="toAccNo")Long toAccNo,@RequestParam(value="amount")Double amount)
	{
		ModelAndView mv=new ModelAndView();
		User user1=userService.getAllDetails(toAccNo);
		if(user1==null || user1.getAccNo()==this.user.getAccNo())
		{
			mv.addObject("message", "Invalid Account Number");
			mv.setViewName("transfer");
			return mv;
		}
		if(amount<0)
		{
			mv.addObject("message", "Enter a positive number");
			mv.setViewName("transfer");
			return mv;
		}
		if(transferService.performTransfer(user.getAccNo(), toAccNo, amount))
		{
			Double balance=balanceService.getBalance(user.getAccNo());
			mv.addObject("balancemsg","Your balance is ");
			mv.addObject("balance",balance);
			mv.addObject("message", "Money Transfered");
			mv.setViewName("transfer");
			return mv;
		}
		else
		{
			mv.addObject("message", "Insufficient Balance");
			mv.setViewName("transfer");
			return mv;
		}
	}
	

	@RequestMapping("/newUser")
	public ModelAndView showLoginPage() {
		return new ModelAndView("UserRegistration", "user", new User());

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
	public ModelAndView UpdateUserController() {
		//User user1 = (User) session.getAttribute("user");
		System.out.println(user);
		return new ModelAndView("UserUpdation", "user", user);
	}

	@RequestMapping("/UserUpdateDb")
	public ModelAndView UpdateUserControllerDB(@ModelAttribute User user) {
//		User user2 = (User) session.getAttribute("user");
		user.setAccNo(this.user.getAccNo());
		System.out.println(user);
		User user1 = userService.updateUser(user);
		if(user1==null)
		{
			return new ModelAndView("UserUpdation", "message", "Password Didnt Match");
		}
		this.user=user1;
		return new ModelAndView("UserUpdation", "message", "Details updated sucessfully");
	}

	@RequestMapping("/displayTransfers")
	public ModelAndView displayingTransfers() {
//		User user = (User) session.getAttribute("user");
		TransferList transferList = transferService.getByAccount(user.getAccNo());
		if (transferList.getTransfer().size() == 0)
			return new ModelAndView("TransfersDisplay", "message", "No Transfers to Display");

		return new ModelAndView("TransfersDisplay", "transferList", transferList.getTransfer());
	}

//	TransactionsDisplay
	@RequestMapping("/displayTransactions")
	public ModelAndView displayTransactions() {
//		User user = (User) session.getAttribute("user");
		TransactionList transactionList = transactionService.getAllTransactionsByAccNo(user.getAccNo());

		if (transactionList.getTransactions().size() == 0)
			return new ModelAndView("TransactionsDisplay", "message", "No transactions To display");
		return new ModelAndView("TransactionsDisplay", "transactionList", transactionList.getTransactions());
	}
	
	@RequestMapping("/userDetailsDisplay")
	public ModelAndView displayDetails()
	{
//		User user1=(User)session.getAttribute("user");
		double balance=balanceService.getBalance(this.user.getAccNo());
		ModelAndView mv=new ModelAndView();
		mv.setViewName("usersDetailsDisplay");
		mv.addObject("user", user);
		mv.addObject("balance",balance);
		return mv;
	}
	
	
	@RequestMapping("/logout")
	public ModelAndView logoutSystem(HttpServletRequest request)
	{
		  HttpSession httpSession = request.getSession();
          httpSession.invalidate();

	   	  return new ModelAndView("welcome");
	}
	
}
