package com.model.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.Admin;
import com.beans.Employee;
import com.beans.EmployeeList;
import com.beans.UserList;
import com.model.persistence.AdminDao;
import com.model.persistence.EmployeeDao;
import com.model.persistence.UserDao;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    { 
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
        return md.digest(input.getBytes(StandardCharsets.UTF_8)); 
    }
    
    public static String toHexString(byte[] hash)
    {
        
        BigInteger number = new BigInteger(1, hash); 
  
        
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
  
        while (hexString.length() < 32) 
        { 
            hexString.insert(0, '0'); 
        } 
  
        return hexString.toString(); 
    }
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private AdminDao adminDao;

	@Override
	public UserList getAllUsers() {
		UserList users=new UserList();
		users.setUsers( userDao.findAll());
		return users;
	}

	@Override
	public EmployeeList getAllEmployees() {
		EmployeeList employees=new EmployeeList();
		employees.setEmployees(employeeDao.findAll());
		return employees;
	}

	@Override
	public Admin validateAdmin(String mailId, String password) {
		return adminDao.validate(mailId, password);
	}
	public Employee addEmployee(Employee employee) {
		try {
			employee.setPassword(toHexString(getSHA(employee.getPassword())));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		employeeDao.save(employee);
		return employee;
	}
}
