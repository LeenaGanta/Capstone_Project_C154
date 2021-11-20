package com.model.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.beans.Transaction;

@Service("balanceService")
public class BalanceServiceImpl implements BalanceService {

	
	@Autowired
	private RestTemplate RestTemplate;
	@Autowired
	private UserService userService;
	
	@Override
	public boolean depositBalance(Long accNo, double amount) {
		try {
		RestTemplate.put("http://localhost:8080/users/"+ accNo+"/"+amount,Double.class);
		Transaction transaction=new Transaction();
		transaction.setAccNo(userService.getAllDetails(accNo));
		transaction.setAmount(amount);
		transaction.setTypeOfTransaction("deposit");
		transaction.setDate(LocalDateTime.now());
		RestTemplate.postForEntity("http://localhost:8080/Transactions/",transaction,Transaction.class);
		return true;
		}
		catch(HttpClientErrorException exc)
		{
			return false;
		}
	}

	@Override
	public boolean withdrawBalance(Long accNo, double amount) {
		try {
			RestTemplate.put("http://localhost:8080/users/withdraw/"+ accNo+"/"+amount,Double.class);
			Transaction transaction=new Transaction();
			transaction.setAccNo(userService.getAllDetails(accNo));
			transaction.setAmount(amount);
			transaction.setTypeOfTransaction("withdraw");
			transaction.setDate(LocalDateTime.now());
			RestTemplate.postForEntity("http://localhost:8080/Transactions/",transaction,Transaction.class);
			return true;
			}
			catch(HttpClientErrorException exc)
			{
				return false;
			}
	}

	@Override
	public Double getBalance(Long accNo) {
		return RestTemplate.getForEntity("http://localhost:8080/users/balance/"+accNo, Double.class).getBody();
	}

}
