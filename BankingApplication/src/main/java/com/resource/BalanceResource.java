package com.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RestController;

import com.model.service.BalanceService;

@RestController
public class BalanceResource {

	@Autowired
	private BalanceService balanceService;
	
	//Working
	@PutMapping(path="/users/{accNo}/{amount}")
	public Double depositBalance(@PathVariable Long accNo, @PathVariable double amount) {
		return balanceService.depositBalance(accNo, amount);
	}
	
	//Working
	@PutMapping(path="/users/withdraw/{accNo}/{amount}")
	public Double withdrawBalance(@PathVariable Long accNo, @PathVariable double amount) {
		return balanceService.withdrawBalance(accNo, amount);
	}
	
	//Working
	@GetMapping(path="/users/balance/{accNo}")
	public Double getBalance(@PathVariable int accNo) {
		return balanceService.getBalance((long)accNo);
	}
	
	
}