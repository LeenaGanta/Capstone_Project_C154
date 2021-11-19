package com.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.BankAccount;
import com.beans.Transfer;
import com.beans.User;
import com.beans.UserList;
import com.model.service.BalanceService;

@RestController
public class BalanceResource {

	@Autowired
	private BalanceService balanceService;
	
	
	//Working
	@GetMapping(path="/users/{accNo}/{amount}")
	public Double depositBalance(@RequestBody Long accNo, @RequestBody double amount) {
		return balanceService.depositBalance(accNo, amount);
	}
	
	@GetMapping(path="/users/{accNo}/{amount}")
	public Double withdrawBalance(@RequestBody Long accNo, @RequestBody double amount) {
		return balanceService.withdrawBalance(accNo, amount);
	}
	
	@GetMapping(path="/users/{accNo}")
	public Double getBalance(@RequestBody Long accNo) {
		return balanceService.getBalance(accNo);
	}
	
	
}