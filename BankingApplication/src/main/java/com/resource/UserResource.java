package com.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beans.User;
import com.model.service.UserService;

@RestController
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	//Working
	@PostMapping(path="/users/{user}")
	public User registerUser(@RequestBody User user)
	{
		return userService.registerUser(user);
	}
	
	//Working
	@GetMapping(path="/users/{accNo}/{password}")
	public User validateUser(@PathVariable("accNo") long accNo,@PathVariable("password") String password)
	{
		return userService.validateUser(accNo, password);
	}
	
	//Working
	@PutMapping(path="/users/user/{user}")
	public User updateUser(@RequestBody User user)
	{
		return userService.updateUser(user);
	}
	
	//Working
	@GetMapping(path="/users/{accNo}")
	public User getAllDetails(@PathVariable("accNo") long accNo)
	{
		return userService.getAllDetails(accNo);
	}

}
