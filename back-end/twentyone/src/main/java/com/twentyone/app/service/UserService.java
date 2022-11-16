package com.twentyone.app.service;

import java.util.Optional;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.twentyone.app.entities.User;

public interface UserService extends BaseService<User, String>{

	public Object processOAuthPostLogin(String email, OAuth2User user);
	public Optional<User> findByEmail(String email);
	public Optional<User> checkLogin(User user);
	
}
