package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Employee;
import com.beans.TransactionList;
import com.beans.TransferList;
import com.beans.User;
import com.beans.UserList;
import com.model.service.BalanceService;
import com.model.service.EmployeeService;
import com.model.service.TransactionService;
import com.model.service.TransferService;
import com.model.service.UserService;

@SessionAttributes({ "employee" })
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private BalanceService balanceService;
	@Autowired
	private TransferService transferService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	UserService userService;
	Employee employee = null;
	User user = null;

	
	
	@RequestMapping("/employeeLoginPage")
	public ModelAndView employeePage() {
		return new ModelAndView("employeeLogin");
	}

	@RequestMapping("/employeeLogin")
	public ModelAndView employeelogin(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password) {
		System.out.println(email + " " + password);
		Employee employeee = employeeService.validateEmployee(email, password);
		if (employeee != null) {
			this.employee = employeee;
			return new ModelAndView("employeedashboard");
		} else {
			return new ModelAndView("employeeLogin", "message", "Please enter valid details");
		}
	}

	@RequestMapping("/employeeDashboard")
	public ModelAndView employeeDashboardPage() {
		return new ModelAndView("employeeDashboard");
	}

	@RequestMapping("/employeeUserPage")
	public ModelAndView employeeUserPage() {
		return new ModelAndView("employeeUserInput");
	}
	
	@RequestMapping("/empCheckBalancePage")
	public ModelAndView checkBalance()
	{
		ModelAndView mv=new ModelAndView();
		Double balance=balanceService.getBalance(user.getAccNo());
		mv.addObject("balancemsg","Your balance is ");
		mv.addObject("balance",balance);
		mv.setViewName("empBalancePage");
		return mv;
	}


	@RequestMapping("/employeeUserInput")
	public ModelAndView getEmployeeByAccount(@RequestParam long accNo) {
		User user1 = employeeService.getDetailsOfUserByAccountNo(accNo);
		System.out.println(user1);
		this.user = user1;
		if (user1 != null) {
			return new ModelAndView("employeeUserDashboard");
		} else {
			return new ModelAndView("employeeUserInput", "message", "Please Enter Valid Account Number");
		}
	}

	@RequestMapping("/employeeUserFunctionsPage")
	public ModelAndView employeeFunctionsPage() {
		return new ModelAndView("employeeUserDashboard");
	}

	@RequestMapping("/displayUsers")
	public ModelAndView displayingUsers() {
		UserList users = employeeService.getAllUsers();

		return new ModelAndView("UsersDisplay", "Users", users.getUsers());
	}

	@RequestMapping("/Employeelogout")
	public ModelAndView logoutSystem(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		return new ModelAndView("welcome");
	}

	@RequestMapping("/empdepositPage")
	public ModelAndView depositPage() {
		return new ModelAndView("empdeposit");
	}

	@RequestMapping("/empdeposit")
	public ModelAndView depositPages(@RequestParam(value = "amount") Double amount) {
		ModelAndView mv = new ModelAndView();
		if (amount < 0) {
			mv.addObject("message", "Please enter a positive number");
			mv.setViewName("empdeposit");
			return mv;
		}

		if (balanceService.depositBalance(user.getAccNo(), amount)) {
			Double balance = balanceService.getBalance(user.getAccNo());
			mv.addObject("balancemsg", "Your balance is ");
			mv.addObject("balance", balance);
			mv.addObject("message", "Amount Deposited");
			mv.setViewName("empdeposit");
			return mv;
		} else {
			mv.addObject("message", "Amount exceeded your balance");
			mv.setViewName("empdeposit");
			return mv;
		}
	}

	@RequestMapping("/empwithdrawPage")
	public ModelAndView withdrawPage() {
		return new ModelAndView("empwithdraw");
	}

	@RequestMapping("/empwithdraw")
	public ModelAndView withdrawPages(@RequestParam(value = "amount") Double amount) {
		ModelAndView mv = new ModelAndView();
		if (amount < 0) {
			mv.addObject("message", "Please enter a positive number");
			mv.setViewName("empwithdraw");
			return mv;
		}
		if (balanceService.withdrawBalance(user.getAccNo(), amount)) {
			Double balance = balanceService.getBalance(user.getAccNo());
			mv.addObject("balancemsg", "Your balance is ");
			mv.addObject("balance", balance);
			mv.addObject("message", "Amount Withdrawn");
			mv.setViewName("empwithdraw");
			return mv;
		} else {
			mv.addObject("message", "Amount exceeded your balance");
			mv.setViewName("empwithdraw");
			return mv;

		}
	}

	@RequestMapping("/emptransferPage")
	public ModelAndView transferPage() {
		return new ModelAndView("emptransfer");
	}

	@RequestMapping("/emptransfer")
	public ModelAndView transferPages(@RequestParam(value = "toAccNo") Long toAccNo,
			@RequestParam(value = "amount") Double amount) {
		ModelAndView mv = new ModelAndView();
		User user1 = userService.getAllDetails(toAccNo);
		System.out.println("emp transfer"+user1+" "+this.user.getAccNo());
		if (user1 == null || user1.getAccNo()==this.user.getAccNo()) {
			mv.addObject("message", "Invalid Account Number");
			mv.setViewName("emptransfer");
			return mv;
		}
		if (amount < 0) {
			mv.addObject("message", "Enter a positive number");
			mv.setViewName("emptransfer");
			return mv;
		}
		if (transferService.performTransfer(user.getAccNo(), toAccNo, amount)) {
			Double balance = balanceService.getBalance(user.getAccNo());
			mv.addObject("balancemsg", "Your balance is ");
			mv.addObject("balance", balance);
			mv.addObject("message", "Money Transfered");
			mv.setViewName("emptransfer");
			return mv;
		} else {
			mv.addObject("message", "Insufficient Balance");
			mv.setViewName("emptransfer");
			return mv;
		}
	}

	@RequestMapping("/empdisplayTransfers")
	public ModelAndView EmployeeransfersDisplay() {
		TransferList transferList = transferService.getByAccount(user.getAccNo());
		if (transferList.getTransfer().size() == 0)
			return new ModelAndView("EmployeeTransfersDisplay", "message", "No Transfers to Display");

		return new ModelAndView("EmployeeTransfersDisplay", "transferList", transferList.getTransfer());
	}

	@RequestMapping("/empdisplayTransactions")
	public ModelAndView EmployeeTransactionsDisplay() {
		TransactionList transactionList = transactionService.getAllTransactionsByAccNo(user.getAccNo());

		if (transactionList.getTransactions().size() == 0)
			return new ModelAndView("EmployeeTransactionsDisplay", "message", "No transactions To display");
		return new ModelAndView("EmployeeTransactionsDisplay", "transactionList", transactionList.getTransactions());
	}

	@RequestMapping("/empupdateDetails")
	public ModelAndView EmployeeUpdateDetails() {
		User user1 = this.user;
		System.out.println(user1);
		return new ModelAndView("emplyeeUserUpdation", "user", user1);
	}

	@RequestMapping("/empolyeeUserUpdateDb")
	public ModelAndView EmployeeUpdateDetailsDb(@ModelAttribute User user, HttpSession session) {
		User user2 = this.user;
		user.setAccNo(user2.getAccNo());
		user.setPassword(user2.getPassword());
		user.setTypeOfAcc(user2.getTypeOfAcc());
		System.out.println(user);
		User user1 = employeeService.updateUserDetails(user);
		System.out.println();

		if (user1 != null) {
			this.user = user1;
			return new ModelAndView("emplyeeUserUpdation", "message", "Details updated sucessfully");
		}
		return new ModelAndView("emplyeeUserUpdation", "message", "Unable to update");
	}

}
