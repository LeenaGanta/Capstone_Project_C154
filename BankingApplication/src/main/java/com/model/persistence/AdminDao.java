package com.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beans.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {
	
	@Query("From Admin where mailId=:mailId and password=:password")
	Admin validate(@Param("mailId") String mailId,@Param("password") String password);

}
