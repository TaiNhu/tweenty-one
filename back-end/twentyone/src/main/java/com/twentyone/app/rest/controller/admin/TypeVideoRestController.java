package com.twentyone.app.rest.controller.admin;

import java.lang.reflect.Array;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twentyone.app.entities.Movie;
import com.twentyone.app.entities.TypeVideo;
import com.twentyone.app.entities.VideoGenres;
import com.twentyone.app.service.CategoriesService;
import com.twentyone.app.service.MovieService;
import com.twentyone.app.service.TypeService;
import com.twentyone.app.service.TypeVideoService;
import com.twentyone.app.service.VideoGenresService;
import com.twentyone.app.entities.Type;


@RestController
@RequestMapping("/admin")
public class TypeVideoRestController {

	
	@Autowired
	TypeVideoService typeVideoService;
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	TypeService typeService;
	
	@Autowired
	VideoGenresService videoGenresService;
	
	@Autowired
	CategoriesService categoriesService;

	@GetMapping("/typevideos")
	public ResponseEntity get() {
		List<TypeVideo> typeVideo = typeVideoService.findAll();
		return ResponseEntity.ok(typeVideo);
	}
	
	@PostMapping("/typevideos")
	public ResponseEntity insert(@RequestBody JsonNode typeVideoJSON) {
		try {
			Optional<Movie> movie = movieService.findById(typeVideoJSON.get("movie").get("id").asInt());
			Optional<Type> type = typeService.findById(typeVideoJSON.get("type").get("id").asInt());
			System.out.println(typeVideoJSON);
			if(movie.isPresent()) {
				TypeVideo typeVideo = null;
				if(typeVideoJSON.get("id") == null) {
					typeVideo = new TypeVideo();
				} else if (typeVideoJSON.get("id") != null) {
					typeVideo = typeVideoService.findById(typeVideoJSON.get("id").asInt()).orElse(new TypeVideo());
				}
				typeVideo.setName(typeVideoJSON.get("name").asText());
				typeVideo.setCount(typeVideoJSON.get("count").asInt());
				typeVideo.setDescription(typeVideoJSON.get("description").asText());
				if(typeVideoJSON.get("image") != null) {
					typeVideo.setImage(typeVideoJSON.get("image").asText());
				}
				typeVideo.setMovie(movie.get());
				typeVideo.setTypeName(typeVideoJSON.get("typeName").asText());
				typeVideo.setType(type.get());
				typeVideoService.insert(typeVideo);
				this.insertVideoGenres(typeVideoJSON, typeVideo);
				return new ResponseEntity("Insert thành công", HttpStatus.OK).ok(typeVideo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity("Lỗi insert", HttpStatus.OK);
		}
		return ResponseEntity.badRequest().build();
	}
	
	private void insertVideoGenres(JsonNode videoGenres, TypeVideo typeVideo) throws Exception {
		System.out.println(typeVideo.getId());
		videoGenresService.removeAll(typeVideo.getId());
		ObjectMapper mapper = new ObjectMapper();
		for(JsonNode videoGenre : videoGenres.get("categories")) {
			VideoGenres vG = new VideoGenres();
			vG.setCategory(categoriesService.findById(videoGenre.asInt()).get());
			vG.setTypeVideo(typeVideo);
			videoGenresService.insert(vG);
		}
	}
	
	
	
	@DeleteMapping("/typevideos/{id}")
	public ResponseEntity insert(@PathVariable int id) {
		try {
			Optional<TypeVideo> typeVideo = typeVideoService.findById(id);
			if(typeVideo.isPresent()) {
				typeVideoService.delete(typeVideo.get());
				return ResponseEntity.ok().build();
			}
			return new ResponseEntity("Sai trường", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity("Lỗi không xác định", HttpStatus.BAD_REQUEST);
		}
	}
	
}
