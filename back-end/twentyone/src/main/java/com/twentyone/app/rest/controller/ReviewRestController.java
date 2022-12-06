package com.twentyone.app.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twentyone.app.service.ReviewService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twentyone.app.entities.*;

@RestController
@RequestMapping("/reviews")
public class ReviewRestController {
	
	@Autowired
	ReviewService reviewService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity reviews(@PathVariable("id") int id){
		return ResponseEntity.ok(reviewService.findByVideoId(id));
	}
	
	@PostMapping("/")
	public ResponseEntity add(@RequestBody JsonNode review) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeVideo typeVideo = mapper.convertValue(review.get("typeVideo"), TypeVideo.class);
			Review r = mapper.convertValue(review, Review.class);
			r.setTypeVideo(typeVideo);
			reviewService.insert(r);
			return ResponseEntity.ok(reviewService.findByVideoId(typeVideo.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity("Lỗi không xác định", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") int id) {
		Optional<Review> review = reviewService.findById(id);
		try {
			reviewService.delete(review.get());
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
		
	}

}
