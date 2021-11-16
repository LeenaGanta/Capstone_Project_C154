package com.model.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beans.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
	@Query("From User where accNo=:accNo and password=:password")
	User validate(@Param("accNo") long accNo,@Param("password") String password);
	
	@Transactional
	@Modifying
	@Query("update User set name=:name,mailId=:mailId,mobileNo=:mobileNo,ssn=:ssn,password=:password where accNo=:accNo ")
	int updateUser(@Param("name")String name,@Param("mailId")String mailId,
	@Param("mobileNo") long mobileNo,@Param("ssn") String ssn,@Param("password")String password, @Param("accNo") long accNo);
	

}
