package com.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.beans.Transaction;
import com.beans.TransactionList;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	RestTemplate restTemplate;
	@Override
	public Transaction preformTransaction(long accNo, double amount, String type) {
		//Transaction transaction=new Transaction(accNo, null, type, amount,LocalDateTime.now())
		return null;
	}

	@Override
	public TransactionList getAllTransactionsByAccNo(long accNo) {
		// TODO Auto-generated method stub
		TransactionList tlist=restTemplate.getForObject("http://localhost:8080/Transactions/"+accNo, TransactionList.class);
		return tlist;
	}

}
