package com.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beans.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Integer> {
@Query("From Employee where empId=:empid and password=:password")
	Employee validate(@Param("empid") int empid,@Param("password") String password);
}
