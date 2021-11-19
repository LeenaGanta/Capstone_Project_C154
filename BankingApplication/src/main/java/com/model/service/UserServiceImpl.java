package com.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.BankAccount;
import com.beans.User;
import com.model.persistence.BankAccountDao;
import com.model.persistence.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BankAccountDao bankAccountDao;
	
	@Override
	public User registerUser(User user) {
		User user1= userDao.save(user);
		bankAccountDao.save(new BankAccount(user1));
		return user1;
	}

	@Override
	public User validateUser(long accNo, String password) {
		return userDao.validate(accNo, password);
	}

	@Override
	public User updateUser(User user) {
		 userDao.updateUser(user.getName(),user.getMailId(), user.getMobileNo(), user.getSsn(), user.getPassword(), user.getAccNo());
		 return userDao.findById(user.getAccNo()).get();
		 
	}

	@Override
	public User getAllDetails(long accNo) {
		return userDao.findById(accNo).get();
	}

}
