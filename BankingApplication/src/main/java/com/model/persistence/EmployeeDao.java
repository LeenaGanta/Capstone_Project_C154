package com.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beans.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Integer> {

}
