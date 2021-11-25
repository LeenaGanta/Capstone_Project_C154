package com.model.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.Employee;
import com.beans.TransferList;
import com.beans.User;
import com.beans.UserList;
import com.model.persistence.EmployeeDao;
import com.model.persistence.UserDao;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
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
	private EmployeeDao employeeDao;
	@Autowired
	private UserService userService;
	@Autowired
	private TransferService transferService;
	@Autowired
	private BalanceService bankAccountService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AdminService adminService;
	
	public TransferList getAllTransfersByAccountNo(long accno) {
		return transferService.getByAccount(accno);
	}
	
	public User getDetailsOfUserByAccountNo(long accno) {
		return userService.getAllDetails(accno);
	}
	
	public User updateUserDetails(User user) {
		int user1 =userDao.updateUser(user.getName(),user.getMailId(), user.getMobileNo(), user.getSsn(), user.getPassword(), user.getAccNo());
		System.out.println("Employee update user "+user1+" "+user );
		 return userDao.findById(user.getAccNo()).get();
		
	}
	
	public double addMoneyByAccountNo(long accno,double amount) {
		return  bankAccountService.depositBalance(accno,amount);
		
	}
	public double withdrawMoneyByAccountNo(long accNo,double amount) {
		return bankAccountService.withdrawBalance(accNo, amount);
	}
	
	public void deleteUser(long accno) {
		Optional<User> user=userDao.findById(accno);
		if(user.isPresent())
		{
			userDao.deleteById(accno);
		}
		
	}
	
	public User createUser(User user) {
		return userService.registerUser(user);
	}
	
	public Employee validateEmployee(String emailId, String password)  {
		try {
			password=toHexString(getSHA(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return employeeDao.validate(emailId, password);
	}

	@Override
	public UserList getAllUsers() {
		return adminService.getAllUsers();
	}
	
}

