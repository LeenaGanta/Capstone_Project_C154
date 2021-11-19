package com.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.Employee;
import com.beans.TransferList;
import com.beans.User;
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
	private BankAccountService bankAccountService;
	@Autowired
	private UserDao userDao;
	
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
		bankAccountService.depositBalance(accno,amount);
		return bankAccountService.getBalance(accno);
	}
	
	public void deleteUser(long accno) {
		userDao.deleteById(accno);
	}
	
	public Employee validateEmployee(int empId, String password) {
		return employeeDao.validate(empId, password);
	}
}
