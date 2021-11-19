package com.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beans.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long>{

	@Query("select transaction from Transaction transaction where txnId=:id")
	List<Transaction> getTransactionsById(@Param("id")long accNO);
}
