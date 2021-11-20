package com.model.service;


import com.beans.Employee;
import com.beans.TransferList;
import com.beans.User;
import com.beans.UserList;

public interface EmployeeService {
	TransferList getAllTransfersByAccountNo(long accno);
	User getDetailsOfUserByAccountNo(long accno);
	double addMoneyByAccountNo(long accno,double amount);
	void deleteUser(long accno);
	Employee validateEmployee(String emailId, String password);
	UserList getAllUsers();
	public User updateUserDetails(User user);
	public double withdrawMoneyByAccountNo(long accNo,double amount);
	public User createUser(User user);
}