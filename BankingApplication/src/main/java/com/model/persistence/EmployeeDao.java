package com.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beans.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Integer> {

	@Query("From Employee where mailId=:emailId and password=:password")
	Employee validate(@Param("emailId") String emailId,@Param("password") String password);
}
