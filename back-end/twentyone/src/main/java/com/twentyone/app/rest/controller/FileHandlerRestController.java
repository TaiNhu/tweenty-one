package com.twentyone.app.rest.controller;

import java.io.File;
import java.io.IOException;

import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.twentyone.app.entities.User;

@RestController
public class FileHandlerRestController {
	
	@ResponseBody
	@PostMapping("/readimage/{path}")
	public ResponseEntity saveProfileImage(@RequestParam("image") MultipartFile file, @PathVariable("path") String path) {
		Boolean isTranfer = com.twentyone.app.utils.SessionUtils.saveProfileImage(file, path);
		return isTranfer ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}

}
