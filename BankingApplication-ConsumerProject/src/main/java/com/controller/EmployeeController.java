package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Employee;
import com.beans.TransferList;
import com.beans.User;
import com.beans.UserList;
import com.model.service.BalanceService;
import com.model.service.EmployeeService;
import com.model.service.TransferService;

@SessionAttributes({"employee"})
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private BalanceService balanceService;
	@Autowired
	private TransferService transferService;
	Employee employee=null;
	User user=null;
	
	@RequestMapping("/employeeLoginPage")
	public ModelAndView employeePage()
	{
		return new ModelAndView("employeeLogin");
	}
	
	@RequestMapping("/employeeLogin")
	public ModelAndView employeelogin(@RequestParam(value = "email") String email,@RequestParam(value="password") String password) {
		System.out.println(email+" "+password);
		Employee employeee=employeeService.validateEmployee(email, password);
		if(employeee!=null) {
			this.employee=employeee;
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
	public ModelAndView getEmployeeByAccount(@RequestParam long accNo) {
		user=employeeService.getDetailsOfUserByAccountNo(accNo);
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
	
	@RequestMapping("/displayUsers")
	public ModelAndView displayingUsers()
	{
		UserList users=employeeService.getAllUsers();
		
		return new ModelAndView("UsersDisplay","Users",users.getUsers());
	}
	@RequestMapping("/Employeelogout")
	public ModelAndView logoutSystem(HttpServletRequest request)
	{
		  HttpSession httpSession = request.getSession();
          httpSession.invalidate();
	   	  return new ModelAndView("Employeelogout","message","Thanks for using our system");
	}
	@RequestMapping("/empdepositPage")
	public ModelAndView depositPage()
	{
		return new ModelAndView("empdeposit");
	}
	
	@RequestMapping("/empdeposit")
	public ModelAndView depositPage(@RequestParam(value="amount") Double amount) {
		ModelAndView mv=new ModelAndView();
		
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
			mv.addObject("message", "Amount exceeded your balance");
			mv.setViewName("deposit");
			return mv;}
	}
	
	@RequestMapping("/empwithdrawPage")
	public ModelAndView withdrawPage()
	{
		return new ModelAndView("empwithdraw");
	}
	
	@RequestMapping("/empwithdraw")
	public ModelAndView withdrawPage(@RequestParam(value="amount") Double amount) {
		ModelAndView mv=new ModelAndView();
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
	
	@RequestMapping("/emptransferPage")
	public ModelAndView transferPage()
	{
		return new ModelAndView("transfer");
	}
	
	@RequestMapping("/emptransfer")
	public ModelAndView transferPage(@RequestParam(value="toAccNo")Long toAccNo,@RequestParam(value="amount")Double amount)
	{
		ModelAndView mv=new ModelAndView();
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
}

