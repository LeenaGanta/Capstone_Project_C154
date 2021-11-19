package com.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.beans.Transaction;
import com.beans.TransactionList;
import com.beans.User;
import com.exceptions.AccountNotFoundException;
import com.exceptions.LowBalanceException;
import com.model.persistence.BankAccountDao;
import com.model.persistence.TransactionDao;
import com.model.persistence.UserDao;

public class TransactionServiceImpl implements TransactionService {

	@Autowired
	UserDao userDao;

	@Autowired
	BankAccountDao bankAccountDao;

	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	BalanceService balanceService;

	@Override
	public Transaction preformTransaction(long accNo, double amount, String type) throws AccountNotFoundException,LowBalanceException
	{
		// TODO Auto-generated method stub
		Optional<User> temp = userDao.findById(accNo);
		if(temp.isEmpty())
		{
			throw new AccountNotFoundException("The requested Account "+ accNo+" does not exists");
		}
		User user=temp.get();
		LocalDateTime now = LocalDateTime.now();
		Transaction transaction = new Transaction();
		transaction.setAccNo(user);
		transaction.setDate(now);
		if (type.equalsIgnoreCase("withdraw")) {
			double balance =balanceService.getBalance(accNo);
			if (balance < amount) {
				throw new LowBalanceException("Insufficient balance please add Rs." + (amount - balance)
						+ " amount in your account to proceed");
			} else {
				balanceService.withdrawBalance(accNo, amount);
				transaction.setAmount(amount);
				transaction.setTypeOfTransaction("withdraw");

			}
		} else {
			balanceService.depositBalance(accNo, amount);
			transaction.setAmount(amount);
			transaction.setTypeOfTransaction("deposit");

		}
		transactionDao.save(transaction);
		return transaction;
	}
	
	public TransactionList getAllTransactionsByAccNo(long accNO)
	{
		List<Transaction> list=transactionDao.getTransactionsById(accNO);
		TransactionList t=new TransactionList(list);
		return t;
		
	}

}
