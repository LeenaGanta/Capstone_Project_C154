package com.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beans.BankAccount;
import com.beans.User;

@Repository
public interface BankAccountDao extends JpaRepository<BankAccount,Integer> {
	
	@Query("From BankAccount where balance=:balance")
	Double getAccountBalance(@Param("accNo") long accNo);

}
