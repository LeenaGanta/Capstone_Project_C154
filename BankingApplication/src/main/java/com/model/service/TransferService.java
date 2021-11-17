package com.model.service;

import com.beans.Transfer;
import com.beans.TransferList;

public interface TransferService {

	Transfer performTransfer(long fromAccountNo,long toAccountNo,double Amount);
	TransferList getByAccount(long accountNo);
}
