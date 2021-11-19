package com.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beans.User;
import com.exceptions.AccountNotFoundException;
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
	public ResponseEntity<User> validateUser(@PathVariable("accNo") long accNo,@PathVariable("password") String password)
	{
		User user=userService.validateUser(accNo, password);
		if(user!=null)
		return ResponseEntity.status(HttpStatus.OK).body(user);
		else
			throw new AccountNotFoundException("Please enter correct credentials");
	}
	
	//Working
	@PutMapping(path="/users/user/{user}")
	public ResponseEntity<User> updateUser(@RequestBody User user)
	{
		User user1=userService.updateUser(user);
		if(user1!=null)
		{ return ResponseEntity.status(HttpStatus.OK).body(user);}
		else
			throw new AccountNotFoundException("Please enter valid details");
	}
	
	//Working
	@GetMapping(path="/users/{accNo}")
	public ResponseEntity<User> getAllDetails(@PathVariable("accNo") long accNo)
	{
		User user=userService.getAllDetails(accNo);
		if(user!=null)
			 return ResponseEntity.status(HttpStatus.OK).body(user);
		else
			throw new AccountNotFoundException("No particulars Found");
		
	}

}
