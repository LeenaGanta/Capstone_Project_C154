package com.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beans.BankAccount;

@Repository
public interface BankAccountDao extends JpaRepository<BankAccount,Integer> {

}
