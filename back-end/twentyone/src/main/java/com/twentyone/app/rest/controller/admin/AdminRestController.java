package com.twentyone.app.rest.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twentyone.app.entities.User;
import com.twentyone.app.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity user(Authentication authentication) {
		List<User> users = userService.findAll();
		return ResponseEntity.ok(users);
	}

}
