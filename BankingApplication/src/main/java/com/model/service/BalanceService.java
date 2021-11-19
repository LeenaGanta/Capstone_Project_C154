package com.model.service;

import com.beans.BankAccount;
import com.beans.Transfer;
import com.beans.User;

public interface BalanceService {

	
	BankAccount depositBalance(User accNo, Transfer amount);
	BankAccount withdrawBalance(User accNo, Transfer amount);
	BankAccount getBalance(User accNo);
	//BankAccount DepositBalance(User accNo, Transfer amount);
}
