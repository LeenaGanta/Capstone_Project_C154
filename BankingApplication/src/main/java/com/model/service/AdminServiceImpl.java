package com.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.Admin;
import com.beans.EmployeeList;
import com.beans.UserList;
import com.model.persistence.AdminDao;
import com.model.persistence.EmployeeDao;
import com.model.persistence.UserDao;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
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

}
