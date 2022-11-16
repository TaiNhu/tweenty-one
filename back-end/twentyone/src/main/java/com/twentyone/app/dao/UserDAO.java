package com.twentyone.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.twentyone.app.entities.User;

public interface UserDAO extends JpaRepository<User, String>{
	
	
	@Query("SELECT u from User u where u.email = :uemail")
	public Optional<User> findByEmail(@Param("uemail") String email);
	
	@Query("SELECT u from User u where u.userName = :userName and password = :password")
	public Optional<User> checkLogin(@Param("userName") String userName, @Param("password") String password);

}
