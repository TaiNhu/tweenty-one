package com.twentyone.app.rest.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twentyone.app.entities.User;
import com.twentyone.app.service.UserService;

@RestController
public class LoginRestController {
	
	
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public ResponseEntity login(@AuthenticationPrincipal OAuth2User user, @RequestParam Optional<String> logout, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if(!logout.isEmpty()) {
	        // token can be revoked here if needed
			if(user != null) {
		        new SecurityContextLogoutHandler().logout(request, null, null);
			}
	        //sending back to client app
			return ResponseEntity.ok().build();
		} else if (logout.isEmpty()) {
			if(user != null) {
				User newUser = (User) userService.processOAuthPostLogin(user.getAttributes().get("email").toString(), user);
	            return ResponseEntity.ok(newUser);
			}
		}
        return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/login")
	public ResponseEntity appLogin(@RequestBody User user) {
		Optional<User> userChecked = userService.checkLogin(user);
		if(!userChecked.isEmpty()) {
			return ResponseEntity.ok(userChecked.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity logout(@AuthenticationPrincipal OAuth2User user, HttpServletRequest request) {
		if(user != null) {
	        new SecurityContextLogoutHandler().logout(request, null, null);
		}
		return ResponseEntity.ok().build();
	}
	
//	@ResponseBody
//	@PostMapping("/registration")
//	public ReponseEntity registration(@RequestBody User user) {
//		
//	}

}
