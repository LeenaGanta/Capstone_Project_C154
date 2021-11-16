package com.model.service;

import com.beans.User;

public interface UserService {
	
	User registerUser(User user);
	User validateUser(long accNo,String password);
	User updateUser(User user);
	User getAllDetails(long accNo);

}
