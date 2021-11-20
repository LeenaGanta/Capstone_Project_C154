package com.model.service;



public interface BalanceService {

	
	Double depositBalance(Long accNo, double amount);
	Double withdrawBalance(Long accNo, double amount);
	Double getBalance(Long accNo);
	
}
