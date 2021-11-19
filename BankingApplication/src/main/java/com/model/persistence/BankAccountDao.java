package com.model.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beans.BankAccount;
import com.beans.User;

@Repository
public interface BankAccountDao extends JpaRepository<BankAccount,Integer> {
	
	@Query("From BankAccount where accNo=:accNo")
	BankAccount getAccountBalance(@Param("accNo") User user);
	
	@Modifying
	@Transactional
	@Query("update BankAccount set balance=:balance where accNo=:accNo")
	int setAccountBalance(@Param("balance")double balance,@Param("accNo")User accNo);

}
