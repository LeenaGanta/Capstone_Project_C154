package com.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import com.beans.TransferList;

@Service("transferService")
public class TransferServiceImpl implements TransferService {

	@Autowired
	private RestTemplate RestTemplate;

	@Override
	public boolean performTransfer(long fromAccountNo, long toAccountNo, double Amount) {
		try {
		RestTemplate.put("http://localhost:8080/transfer/"+fromAccountNo+"/"+toAccountNo+"/"+Amount, Double.class);
		return true;
		}
		catch(HttpClientErrorException exc)
		{
		return false;
		}
		
	}

	@Override
	public TransferList getByAccount(long accountNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
