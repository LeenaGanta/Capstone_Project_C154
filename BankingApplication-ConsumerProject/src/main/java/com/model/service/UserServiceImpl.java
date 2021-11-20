package com.model.service;

import org.springframework.web.client.HttpClientErrorException;
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
	public User validateUser(long accNo, String password) {
		try {
		ResponseEntity<User> user=RestTemplate.getForEntity("http://localhost:8080/users/"+accNo+"/"+password,User.class);
		return user.getBody();
		}
		catch(HttpClientErrorException exc)
		{
			return null;
		}
		
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getAllDetails(long accNo) {
		ResponseEntity<User> user=RestTemplate.getForEntity("http://localhost:8080/users/"+accNo, User.class);
		return user.getBody();
	}

}
