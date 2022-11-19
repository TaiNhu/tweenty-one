package com.twentyone.app.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twentyone.app.entities.TypeVideo;
import com.twentyone.app.service.TypeVideoService;

@RestController
@RequestMapping("/films")
public class VideoRestController {

	@Autowired
	TypeVideoService typeVideoService;
	
	@GetMapping("/lastest")
	public Object searchVideo() {
		int[] a = {};
		String[] b = {"name", "DESC"};
		return typeVideoService.filter(a, b);
	}
	
	@GetMapping("/categories")
	public Object filter(@RequestParam("categories") Optional<int[]> categories, @RequestParam("sort") Optional<String[]> sort) {
		int[] a = {};
		String[] b = {"name", "ASC"};
		return typeVideoService.filter(categories.orElse(a), sort.orElse(b));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getVideo(@PathVariable("id") int id) {
		List<TypeVideo> typeVideos = typeVideoService.findSameAnother(id);
		return ResponseEntity.ok(typeVideos);
	}
	
	@GetMapping("/type")
	public Object typeFilter(@RequestParam("types") Optional<int[]> types, @RequestParam("sort") Optional<String[]> sort) {
		int[] a = {};
		String[] b = {"name", "ASC"};
		return typeVideoService.typeFilter(types.orElse(a), sort.orElse(b));
	}
	
	
}
