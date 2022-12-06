package com.twentyone.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.twentyone.app.service.UserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	UserService userService;

	@Autowired
	BCryptPasswordEncoder pe;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			com.twentyone.app.entities.User user = userService.findById(username).get();
			String password = pe.encode(user.getPassword());
			String role = user.getRole();
			return User.withUsername(username).password(password).roles(role).build();
		} catch (NoSuchElementException e) {
			throw new UsernameNotFoundException(username + " not found!");
		}
	}
}
