package com.twentyone.app.rest.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.twentyone.app.entities.User;
import com.twentyone.app.service.UserService;

@RestController
public class FileHandlerRestController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/saveimage/avatar", method = RequestMethod.POST)
	public ResponseEntity saveProfileImage(@RequestParam("image") MultipartFile file) {
		Optional<User> user = userService.findById(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		if (user.isPresent()) {
			String fileName = com.twentyone.app.utils.SessionUtils.saveProfileImage(file);
			user.get().setImage(fileName);
			try {
				userService.update(user.get());
				return ResponseEntity.ok(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity("Lỗi", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/saveimage", method = RequestMethod.POST)
	public ResponseEntity saveImage(@RequestParam("image") MultipartFile file) {
		String fileName = com.twentyone.app.utils.SessionUtils.saveProfileImage(file);
		Map response = new HashMap();
		response.put("fileName", fileName);
		return ResponseEntity.ok(response);
	}

}
