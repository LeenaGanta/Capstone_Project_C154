package com.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Employee;
import com.beans.TransferList;
import com.beans.User;
import com.beans.UserList;
import com.exceptions.AccountNotFoundException;
import com.model.service.EmployeeService;

@RestController
public class EmployeeResource {
	@Autowired
	private EmployeeService employeeService;
	
	//Working
	@PutMapping(path="/employees/users/deposit/{accNo}/{amount}")
	public Double depositBalance(@PathVariable Long accNo, @PathVariable double amount) {
		return employeeService.addMoneyByAccountNo(accNo, amount);
	}
	
	//Working
	@PutMapping(path="/employees/users/withdraw/{accNo}/{amount}")
	public Double withdrawBalance(@PathVariable Long accNo, @PathVariable double amount) {
		return employeeService.withdrawMoneyByAccountNo(accNo, amount);
	}
	
	//Working
	@GetMapping(path="/employees/users/{accNo}")
	public ResponseEntity<User> getAllDetails(@PathVariable("accNo") long accNo)
	{
		User user=employeeService.getDetailsOfUserByAccountNo(accNo);
		if(user!=null)
			 return ResponseEntity.status(HttpStatus.OK).body(user);
		else
			throw new AccountNotFoundException("No particulars Found");
		
	}
	
	@DeleteMapping(path = "/employees/users/delete/{accNo}")
	public void deleteUser(@PathVariable long accNo) {
		employeeService.deleteUser(accNo);
	}
	
	//Working
	@GetMapping(path="/employees/users")
	ResponseEntity<UserList> getAllUsers()
	{
		UserList users=employeeService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	//Working
	@GetMapping(path="/employees/users/transfers/{accNo}")
	public TransferList getAllTransfersByAccountNo(@PathVariable("accNo") long accNo) {
		return employeeService.getAllTransfersByAccountNo(accNo);
	}
	
	//Working
	@GetMapping(path="/employee/{emailId}/{password}")
	public Employee validateEmployee(@PathVariable("emailId") String emailId,@PathVariable("password") String password)
	{
		return employeeService.validateEmployee(emailId, password);
	}
	
	//Working
	@PutMapping(path="/employees/update/{user}")
	public ResponseEntity<User> updateUser(@RequestBody User user)
	{
		User user1=employeeService.updateUserDetails(user);
		if(user1!=null)
		{ return ResponseEntity.status(HttpStatus.OK).body(user1);}
		else
			throw new AccountNotFoundException("Please enter valid details");
	}
	
	@PostMapping(path="/employees/user/{user}")
	public User registerUser(@RequestBody User user)
	{
		return employeeService.createUser(user);
	}

}
