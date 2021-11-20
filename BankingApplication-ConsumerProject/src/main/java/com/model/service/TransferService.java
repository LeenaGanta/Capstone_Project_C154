package com.model.service;


import com.beans.TransferList;

public interface TransferService {

	boolean performTransfer(long fromAccountNo,long toAccountNo,double Amount);
	TransferList getByAccount(long accountNo);
}