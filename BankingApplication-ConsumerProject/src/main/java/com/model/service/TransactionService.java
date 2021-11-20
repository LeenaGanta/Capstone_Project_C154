package com.model.service;

import com.beans.Transaction;
import com.beans.TransactionList;


public interface TransactionService {
	
	public Transaction preformTransaction(long accNo, double amount, String type) ;
	public TransactionList getAllTransactionsByAccNo(long accNO);
}
