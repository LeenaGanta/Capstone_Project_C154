package com.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
	public Double depositBalance(Long accNo,double amount) {

		boolean exist= userDao.findById(accNo).isPresent();
		double balance=bankAccountDao.getAccountBalance(accNo);
		
		if(exist) {
			throw new AccountNotFoundException("Wrong Account No. "+accNo);
		}
		else {
			
				balance+=amount;
		}
		
		return balance;
	}

	@Override
	public Double withdrawBalance(Long accNo,double amount) {
		Double balance=bankAccountDao.getAccountBalance(accNo);
			if(balance<amount) {
				throw new LowBalanceException("Insufficient balance please add Rs."+(amount-balance)+" amount in your account to proceed");
			}
			else {
				balance=balance-amount;
				
			}
		return balance;
		
	}

	@Override
	public Double getBalance(Long accNo) {
		return bankAccountDao.getAccountBalance(accNo);
	}
	
	

}
