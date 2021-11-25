package com.model.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.BankAccount;
import com.beans.User;
import com.model.persistence.BankAccountDao;
import com.model.persistence.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService {
	public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    { 
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8)); 
    }
    
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash); 
  
        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
  
        // Pad with leading zeros
        while (hexString.length() < 32) 
        { 
            hexString.insert(0, '0'); 
        } 
  
        return hexString.toString(); 
    }
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BankAccountDao bankAccountDao;
	
	@Override
	public User registerUser(User user) {
		User user1= userDao.save(user);
		try {
			user1.setPassword(toHexString(getSHA(user1.getPassword())));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		bankAccountDao.save(new BankAccount(user1));
		return user1;
	}

	@Override
	public User validateUser(long accNo, String password) {
		try {
			password=toHexString(getSHA(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return userDao.validate(accNo, password);
	}

	@Override
	public User updateUser(User user) {
		User user1=userDao.findById(user.getAccNo()).get();
		String password=null;
			try {
				password=toHexString(getSHA(user.getPassword()));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			if(!user1.getPassword().equals(password))
			{
				return null;
			}
			
		 userDao.updateUser(user.getName(),user.getMailId(), user.getMobileNo(), user.getSsn(), password, user.getAccNo());
		 return userDao.findById(user.getAccNo()).get();
		 
	}

	@Override
	public User getAllDetails(long accNo) {
		return userDao.findById(accNo).get();
	}

}
