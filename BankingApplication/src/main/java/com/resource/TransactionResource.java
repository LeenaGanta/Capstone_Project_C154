package com.resource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Transaction;
import com.beans.TransactionList;
import com.model.service.TransactionService;

@RestController
@RequestMapping("/Transactions")
public class TransactionResource {
	
	@Autowired
	TransactionService transactionService;
	
	//Working
	@PostMapping(path="/")
	Transaction performTransactionResource(@RequestBody  Transaction transaction)
	{
		System.out.println(transaction);
		transactionService.preformTransaction(transaction.getAccNo().getAccNo(), transaction.getAmount(), transaction.getTypeOfTransaction());
		return transaction;
	}
	
	//Working
	@GetMapping(path="/{accNo}")
	TransactionList getTranactionsContoller(@PathVariable("accNo")  long accNo)
	{
		return transactionService.getAllTransactionsByAccNo(accNo);
	}

}
