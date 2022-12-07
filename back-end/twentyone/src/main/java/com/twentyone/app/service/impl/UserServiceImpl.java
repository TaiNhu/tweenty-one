package com.twentyone.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.twentyone.app.dao.UserDAO;
import com.twentyone.app.entities.User;
import com.twentyone.app.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO uDAO;

	@Override
	public void insert(User entity) {
		// TODO Auto-generated method stub
		uDAO.save(entity);
	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub
		uDAO.save(entity);
		
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		uDAO.delete(entity);
		
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return uDAO.findAll();
	}

	@Override
	public Optional<User> findById(String key) {
		// TODO Auto-generated method stub
		return uDAO.findById(key);
	}
	
	public Optional<User> findByEmail(String email) {
		return uDAO.findByEmail(email);
	}
	
	 public Object processOAuthPostLogin(String email, OAuth2User user) {
	        Optional<User> existUser = this.findByEmail(email);
	         
	        if (existUser.isEmpty()) {
	        	System.out.println("User is not exist");
	            User newUser = new User();
	            newUser.setUserName(user.getName());
	            newUser.setEmail(email);
	            newUser.setRole("USER");
	            newUser.setImage(user.getAttributes().get("picture").toString());
	            uDAO.save(newUser);
	            return newUser;
	        }
	        return existUser.get();
	         
	 }
	 
	 public Optional<User> checkLogin(User user){
		return uDAO.checkLogin(user.getUserName(), user.getPassword());
	 }

}
