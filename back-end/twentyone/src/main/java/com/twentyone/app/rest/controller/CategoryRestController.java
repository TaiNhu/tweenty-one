package com.twentyone.app.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twentyone.app.service.CategoriesService;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

	@Autowired
	CategoriesService categoriesService;
	
	
	@GetMapping("/")
	public ResponseEntity getAll() {
		return ResponseEntity.ok(categoriesService.findAll());
	}
	
}
