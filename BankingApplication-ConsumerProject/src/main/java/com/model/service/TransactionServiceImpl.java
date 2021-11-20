package com.model.service;


import com.beans.Transaction;
import com.beans.TransactionList;

public class TransactionServiceImpl implements TransactionService {

	@Override
	public Transaction preformTransaction(long accNo, double amount, String type) {
		//Transaction transaction=new Transaction(accNo, null, type, amount,LocalDateTime.now())
		return null;
	}

	@Override
	public TransactionList getAllTransactionsByAccNo(long accNO) {
		// TODO Auto-generated method stub
		return null;
	}

}
