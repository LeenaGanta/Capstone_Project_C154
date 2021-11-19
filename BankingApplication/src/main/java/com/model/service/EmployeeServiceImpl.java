package com.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.Employee;
import com.beans.TransferList;
import com.beans.User;
import com.beans.UserList;
import com.model.persistence.EmployeeDao;
import com.model.persistence.UserDao;
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private UserService userService;
	@Autowired
	private TransferService transferService;
	@Autowired
	private BalanceService bankAccountService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AdminService adminService;
	
	public TransferList getAllTransfersByAccountNo(long accno) {
		return transferService.getByAccount(accno);
	}
	
	public User getDetailsOfUserByAccountNo(long accno) {
		return userService.getAllDetails(accno);
	}
	
	public User updateUserDetails(User user) {
		return userService.updateUser(user);
		
	}
	
	public double addMoneyByAccountNo(long accno,double amount) {
		return  bankAccountService.depositBalance(accno,amount);
		
	}
	public double withdrawMoneyByAccountNo(long accNo,double amount) {
		return bankAccountService.withdrawBalance(accNo, amount);
	}
	
	public void deleteUser(long accno) {
		Optional<User> user=userDao.findById(accno);
		if(user.isPresent())
		{
			userDao.deleteById(accno);
		}
		
	}
	
	public User createUser(User user) {
		return userService.registerUser(user);
	}
	
	public Employee validateEmployee(String emailId, String password) {
		return employeeDao.validate(emailId, password);
	}

	@Override
	public UserList getAllUsers() {
		return adminService.getAllUsers();
	}
}

