package com.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.beans.Transfer;

import com.beans.User;

@Repository
public interface TransferDao extends JpaRepository<Transfer, Long> {

	@Query("from Transfer where fromAccNo=:accno")
	List<Transfer> getTransferByAccountNo(@Param("accno") User user);
}
