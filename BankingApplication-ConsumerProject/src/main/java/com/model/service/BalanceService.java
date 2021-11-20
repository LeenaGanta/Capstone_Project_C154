package com.model.service;



public interface BalanceService {

	
	boolean depositBalance(Long accNo, double amount);
	boolean withdrawBalance(Long accNo, double amount);
	Double getBalance(Long accNo);
	
}
