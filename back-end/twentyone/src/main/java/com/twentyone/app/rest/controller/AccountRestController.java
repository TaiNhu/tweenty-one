package com.twentyone.app.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twentyone.app.entities.User;
import com.twentyone.app.service.UserService;

@RestController
@RequestMapping("/user")
public class AccountRestController {

	@Autowired
	UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity getUser(@PathVariable("id") String id) {
		Optional<User> user = userService.findById(id);
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}
		return new ResponseEntity("User not found", HttpStatus.NO_CONTENT);
	}
	
}
