package com.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beans.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long>{

}
