package com.model.service;

import org.springframework.stereotype.Service;

import com.beans.Employee;
import com.beans.TransferList;
import com.beans.User;
import com.beans.UserList;

@Service
public class EmpolyeeServiceImpl implements EmployeeService {

	@Override
	public TransferList getAllTransfersByAccountNo(long accno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getDetailsOfUserByAccountNo(long accno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double addMoneyByAccountNo(long accno, double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteUser(long accno) {
		// TODO Auto-generated method stub

	}

	@Override
	public Employee validateEmployee(String emailId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserList getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUserDetails(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double withdrawMoneyByAccountNo(long accNo, double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
