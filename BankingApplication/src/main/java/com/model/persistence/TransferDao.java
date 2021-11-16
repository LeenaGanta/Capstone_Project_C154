package com.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.beans.Transfer;

@Repository
public interface TransferDao extends JpaRepository<Transfer, Long> {

}
