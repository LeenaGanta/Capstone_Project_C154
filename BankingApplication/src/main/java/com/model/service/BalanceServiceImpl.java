package com.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Double depositBalance(Long accNo,double amount) {

	
		boolean exist= userDao.findById(accNo).isPresent();
		Optional<User> user=userDao.findById(accNo); 
		double balance=bankAccountDao.getAccountBalance(user.get()).getBalance();
		
		if(!exist) {
			throw new AccountNotFoundException("Wrong Account No. "+accNo);
		}
		else {
			
				balance+=amount;
				bankAccountDao.setAccountBalance(balance, user.get());
		}
		
		return balance;
	}

	@Override
	public Double withdrawBalance(Long accNo,double amount) {
		Optional<User> user=userDao.findById(accNo);
		Double balance=bankAccountDao.getAccountBalance(user.get()).getBalance();
			if(balance<amount) {
				throw new LowBalanceException("Insufficient balance please add Rs."+(amount-balance)+" amount in your account to proceed");
			}
			else {
				balance=balance-amount;
				bankAccountDao.setAccountBalance(balance, user.get());
				
			}
		return balance;
		
	}

	@Override
	public Double getBalance(Long accNo) {
		System.out.println("================");
		Optional<User> user=userDao.findById(accNo);
		System.out.println(bankAccountDao.getAccountBalance(user.get()).toString());
		return bankAccountDao.getAccountBalance(user.get()).getBalance();
	}
	
	

}
