package com.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beans.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {

}
