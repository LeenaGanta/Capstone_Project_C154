package com.model.service;

import org.springframework.web.client.RestTemplate ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.beans.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private RestTemplate RestTemplate;
	
	
	@Override
	public User registerUser(User user) {
		return null;
	}

	@Override
	public boolean validateUser(long accNo, String password) {
		ResponseEntity<User> user=RestTemplate.getForEntity("http://localhost:8080/users/"+accNo+"/"+password,User.class);
		if(user.getBody()!=null)
			return true;
		else
			return false;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getAllDetails(long accNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
