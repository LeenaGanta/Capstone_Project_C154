package com.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.beans.Admin;
import com.beans.Employee;
import com.beans.EmployeeList;
import com.beans.User;
import com.beans.UserList;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private RestTemplate RestTemplate;

	@Override
	public boolean validateAdmin(String mailId, String password) {
		try {
			ResponseEntity<Admin> admin = RestTemplate.getForEntity("http://localhost:8080/admin/" + mailId + "/" + password, Admin.class);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public UserList getAllUsers() {
		ResponseEntity<UserList> user = RestTemplate.getForEntity("http://localhost:8080/users/", UserList.class);
		return user.getBody();
	}

	@Override
	public EmployeeList getAllEmployees() {
		ResponseEntity<EmployeeList> employee = RestTemplate.getForEntity("http://localhost:8080/employees/",
				EmployeeList.class);
		return employee.getBody();
	}

	@Override
	public Employee addEmployee(Employee employee) {
		ResponseEntity<Employee> employee1=RestTemplate.postForEntity("http://localhost:8080/admin/add/post", employee, Employee.class);
		return employee1.getBody();
	}
	
	

}
