package com.model.service;

import com.beans.Transaction;
import com.beans.TransactionList;
import com.exceptions.AccountNotFoundException;
import com.exceptions.LowBalanceException;

public interface TransactionService {
	
	public Transaction preformTransaction(long accNo, double amount, String type) throws AccountNotFoundException,LowBalanceException;
	public TransactionList getAllTransactionsByAccNo(long accNO);
}
