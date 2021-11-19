package com.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping(path="/",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	Transaction performTransactionResource(Transaction transaction)
	{
		System.out.println(transaction);
		return transaction;
	}
	@GetMapping(path="/{accNo}")
	TransactionList getTranactionsContoller(@PathVariable("accNo")  long accNo)
	{
		return transactionService.getAllTransactionsByAccNo(accNo);
	}

}
