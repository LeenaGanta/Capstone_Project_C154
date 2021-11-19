package com.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Transfer;
import com.beans.TransferList;
import com.model.service.TransferService;

@RestController
public class TransferResource {

	@Autowired
	private TransferService transferService;
	
	@PutMapping(path = "/transfer/{fromAccountNo}/{toAccountNo}/{Amount}")
	public Transfer transfer(@PathVariable long fromAccountNo,@PathVariable long toAccountNo,@PathVariable double Amount) {
		return transferService.performTransfer(fromAccountNo, toAccountNo, Amount);
	}
	@GetMapping(path = "/transfers/{accountNo}")
	public TransferList getAllTransfersByAccountNo(@PathVariable long accountNo) {
		return transferService.getByAccount(accountNo);
	}
}