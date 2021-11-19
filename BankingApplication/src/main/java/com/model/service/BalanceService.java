package com.model.service;

import com.beans.BankAccount;
import com.beans.User;

public interface BalanceService {

	
	Double depositBalance(Long accNo, double amount);
	Double withdrawBalance(Long accNo, double amount);
	Double getBalance(Long accNo);
	//BankAccount DepositBalance(User accNo, Transfer amount);
}
