package com.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.beans.Employee;
import com.beans.TransactionList;
import com.beans.TransferList;
import com.beans.User;
import com.beans.UserList;
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	RestTemplate restTemplate;
	@Override
	public TransferList getAllTransfersByAccountNo(long accno) {
		ResponseEntity<TransferList> transferList=restTemplate.getForEntity("http://localhost:8080/employees/users/transfers/"+accno, TransferList.class);
		return transferList.getBody();
	}

	@Override
	public User getDetailsOfUserByAccountNo(long accno) {
		ResponseEntity<User> user;
	
		try {
		user=restTemplate.getForEntity("http://localhost:8080/users/"+accno, User.class);
		}
		catch(Exception e)
		{
			return null;
		}
		return user.getBody();
	}

	

	@Override
	public double addMoneyByAccountNo(long accno, double amount) {
	ResponseEntity<Double> bal =restTemplate.getForEntity("http://localhost:8080//employees/users/deposit/"+accno+"/"+amount, Double.class);
		return bal.getBody();
	}

	@Override
	public void deleteUser(long accno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee validateEmployee(String emailId, String password) {
		ResponseEntity<Employee> employee= restTemplate.getForEntity("http://localhost:8080/employee/"+emailId+"/"+password, Employee.class);
		return employee.getBody();
	}

	@Override
	public UserList getAllUsers() {
		ResponseEntity<UserList> userList = restTemplate.getForEntity("http://localhost:8080/employees/users", UserList.class);
		return userList.getBody();
	}

	@Override
	public User updateUserDetails(User user) {
		
//		ResponseEntity<User> user;
		System.out.println("Employee user update "+ user);
		
		try {
		restTemplate.put("http://localhost:8080/employees/update/put", user,User.class);
		}
		catch(Exception e)
		{
			return null;
		}
		return user;
	}

	@Override
	public double withdrawMoneyByAccountNo(long accNo, double amount) {
		ResponseEntity<Double> bal =restTemplate.getForEntity("http://localhost:8080//employees/users/withdraw/"+accNo+"/"+amount, Double.class);
		return bal.getBody();
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
