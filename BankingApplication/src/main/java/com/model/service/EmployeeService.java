package com.model.service;

import com.beans.Employee;
import com.beans.TransferList;
import com.beans.User;

public interface EmployeeService {
	TransferList getAllTransfersByAccountNo(long accno);
	User getDetailsOfUserByAccountNo(long accno);
	double addMoneyByAccountNo(long accno,double amount);
	void deleteUser(long accno);
	Employee validateEmployee(int empId, String password);
}
