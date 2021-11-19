package com.model.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.beans.BankAccount;
import com.beans.Transfer;
import com.beans.TransferList;
import com.beans.User;
import com.exceptions.AccountNotFoundException;
import com.exceptions.LowBalanceException;
import com.model.persistence.BankAccountDao;
import com.model.persistence.TransferDao;
import com.model.persistence.UserDao;


@Service
public class TransferServiceImpl implements TransferService{
	@Autowired
	private TransferDao transferDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BankAccountDao bankAccountDao;
	
	@Autowired
	BalanceService balanceService;
	
	@Override
	public Transfer performTransfer(long fromAccountNo, long toAccountNo, double Amount) {
		boolean exist= userDao.findById(toAccountNo).isPresent();
//		boolean exist= restTemplate.getForObject(null, Boolean.class);
		if(exist) {
			throw new AccountNotFoundException("Wrong Account No. "+toAccountNo);
		}
			
		else {
		
			double balance=balanceService.getBalance(fromAccountNo);
//			double balance= restTemplate.getForObject(null, Double.class);
			if(balance<Amount) {
				throw new LowBalanceException("Insufficient balance please add Rs."+(Amount-balance)+" amount in your account to proceed");
			}
			else {
				balanceService.withdrawBalance(fromAccountNo,Amount);
				balanceService.depositBalance(toAccountNo,Amount);
			
		LocalDateTime now = LocalDateTime.now();
		
		User to = userDao.findById(toAccountNo).get();

		
		User from = userDao.findById(fromAccountNo).get();

		
		Transfer transfer=new Transfer(from, to, Amount, now);
		
		transferDao.save(transfer);
		
		return transfer;}}
	}
	
	@Override
	public TransferList getByAccount(long accountNo) {
		TransferList list= new TransferList();
		list.setTransfer(transferDao.getTransferByAccountNo(accountNo));
		
		return list;
	}

	
	
}