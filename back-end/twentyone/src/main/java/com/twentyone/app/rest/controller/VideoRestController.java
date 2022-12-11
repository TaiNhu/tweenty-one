package com.twentyone.app.rest.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twentyone.app.entities.Episode;
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
		String[] b = {"name", "ASC"};
		return typeVideoService.filter(a, Optional.of(""),  b);
	}
	
	@GetMapping("/categories")
	public Object filter(@RequestParam("categories") Optional<int[]> categories,@RequestParam("name") Optional<String> name, @RequestParam("sort") Optional<String[]> sort) {
		int[] a = {};
		String[] b = {"name", "ASC"};
		return typeVideoService.filter(categories.orElse(a), name, sort.orElse(b));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getVideo(@PathVariable("id") int id) {
		List<TypeVideo> typeVideos = new ArrayList<TypeVideo>();
		typeVideos.add(typeVideoService.findById(id).get());
		typeVideos.addAll(typeVideoService.findSameAnother(id));
		return ResponseEntity.ok(typeVideos);
	}
	
	@GetMapping("/type")
	public Object typeFilter(@RequestParam("types") Optional<int[]> types, @RequestParam("sort") Optional<String[]> sort) {
		int[] a = {};
		String[] b = {"name", "ASC"};
		return typeVideoService.typeFilter(types.orElse(a), sort.orElse(b));
	}
	
	@GetMapping("/avgscore/{id}")
	public ResponseEntity getAvg(@PathVariable("id") int id) {
		return ResponseEntity.ok(typeVideoService.getAvgScore(id));
	}
	
	@GetMapping("/lastestcomment")
	public ResponseEntity getLastestComment() {
		return ResponseEntity.ok(typeVideoService.findByReviewASC());
	}
	
	
	@PostMapping("")
	public ResponseEntity post(@RequestBody TypeVideo typeVideo) {
		try {
			typeVideoService.insert(typeVideo);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity("Lỗi không xác định", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
