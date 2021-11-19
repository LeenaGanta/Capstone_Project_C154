package com.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.beans.BankAccount;
import com.beans.Transfer;
import com.beans.User;
import com.exceptions.AccountNotFoundException;
import com.exceptions.LowBalanceException;
import com.model.persistence.BankAccountDao;
import com.model.persistence.UserDao;

@Service("balanceService")
public class BalanceServiceImpl implements BalanceService{

	@Autowired
	private BankAccountDao bankAccountDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public BankAccount depositBalance(User accNo,Transfer amount) {

		boolean exist= userDao.findById(accNo).isPresent();
		BankAccount balance=bankAccountDao.getAccountBalance(accNo);
		
		if(exist) {
			throw new AccountNotFoundException("Wrong Account No. "+accNo);
		}
		else {
			
				balance=balance+amount;
				
			
		}
		
		return balance;
	}

	@Override
	public BankAccount withdrawBalance(User accNo,Transfer amount) {
		BankAccount balance=bankAccountDao.getAccountBalance(accNo);
			if(balance<amount) {
				throw new LowBalanceException("Insufficient balance please add Rs."+(amount-balance)+" amount in your account to proceed");
			}
			else {
				balance=balanc2e-amount;
				
			}
		return balance;
		
	}

	@Override
	public BankAccount getBalance(User accNo) {
		return bankAccountDao.getAccountBalance(accNo);
	}
	
	

}
