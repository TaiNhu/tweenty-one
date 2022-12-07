package com.twentyone.app.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twentyone.app.service.TypeService;

@RestController
@RequestMapping("/types")
public class TypeRestController {

	@Autowired
	TypeService typeService;
	
	@GetMapping("")
	public ResponseEntity get() {
		return ResponseEntity.ok(typeService.findAll());
	}
	
}
