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
		user.setAccNo(20);
		User x=RestTemplate.postForObject("http://localhost:8080/users/post", user, User.class);
		System.out.println(x);
		return x;
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
		try
		{
		RestTemplate.put("http://localhost:8080/users/user/put", user,User.class);
		}
		catch(Exception e)
		{
			return null;
		}
		return user;
	}

	@Override
	public User getAllDetails(long accNo) {
		try
		{
		ResponseEntity<User> user=RestTemplate.getForEntity("http://localhost:8080/users/"+accNo, User.class);
		return user.getBody();
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
