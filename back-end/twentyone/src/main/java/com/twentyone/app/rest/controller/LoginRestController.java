package com.twentyone.app.rest.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.twentyone.app.entities.User;
import com.twentyone.app.service.UserService;

import lombok.NonNull;

@CrossOrigin
@RestController
public class LoginRestController {
	
	
	@Autowired
	UserService userService;

	@Autowired
    private AuthenticationManager authenticationManager;
	
	@GetMapping("/login")
	public ResponseEntity login(@AuthenticationPrincipal OAuth2User user, @RequestParam Optional<String> logout, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			return ResponseEntity.ok(userService.findById(SecurityContextHolder.getContext().getAuthentication().getName().toString()));
		}
        return ResponseEntity.badRequest().build();
	}
	
	@RequestMapping(value = "/login", method =RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity appLogin(@RequestBody User user) {
		Optional<User> userChecked = userService.checkLogin(user);
		if(userChecked.isPresent()) {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return ResponseEntity.ok(userChecked);
		}
		return ResponseEntity.badRequest().build();
		
	}
	
	@GetMapping("/logout")
	public ResponseEntity logout(@AuthenticationPrincipal OAuth2User user, HttpServletRequest request) {
		if(user != null) {
	        new SecurityContextLogoutHandler().logout(request, null, null);
		}
		return ResponseEntity.ok().build();
	}
	
	
	@RequestMapping(value = "/registration", method =RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> registration(@RequestBody Optional<User> user) {
		try {
			if(!user.isEmpty()) {
				userService.insert(user.get());
				return ResponseEntity.ok(user.get());
			}
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

}
