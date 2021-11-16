package com.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.User;
import com.model.persistence.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User registerUser(User user) {
		return userDao.save(user);
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
