package com.twentyone.app.rest.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twentyone.app.entities.Episode;
import com.twentyone.app.entities.Movie;
import com.twentyone.app.entities.TypeVideo;
import com.twentyone.app.service.MovieService;

@RestController
@RequestMapping("/admin")
public class MovieRestController {
	
	
	@Autowired
	MovieService movieService;

	@GetMapping("/movies")
	public ResponseEntity get() {
		List<Movie> movies = movieService.findAll();
		return new ResponseEntity("Thành công", HttpStatus.OK).ok(movies);
	}
	
	@PostMapping("/movies")
	public ResponseEntity insert(@RequestBody Movie movie) {
		try {
			movieService.insert(movie);
			return new ResponseEntity("Thành công", HttpStatus.OK).ok(movie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity("Lỗi không xác định", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping("/movies/{id}")
	public ResponseEntity update(@PathVariable("id") int id, @RequestBody Movie movieUpdate) {
		Optional<Movie> movie = movieService.findById(id);
		if(movie.isPresent()) {
			movie.get().setName(movieUpdate.getName());
			try {
				movieService.update(movie.get());
				return ResponseEntity.ok(movie.get());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity("Lỗi không xác định", HttpStatus.EXPECTATION_FAILED);
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
	
	@DeleteMapping("/movies/{id}")
	public ResponseEntity insert(@PathVariable int id) {
		try {
			Optional<Movie> movie = movieService.findById(id);
			if(movie.isPresent()) {
				movieService.delete(movie.get());
				return new ResponseEntity("Thành công", HttpStatus.OK);
			}
			return new ResponseEntity("Sai trường", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity("Lỗi không xác định", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
