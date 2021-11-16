package com.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beans.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

}
