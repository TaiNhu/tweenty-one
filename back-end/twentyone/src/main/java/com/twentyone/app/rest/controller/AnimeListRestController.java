package com.twentyone.app.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twentyone.app.entities.AnimeList;
import com.twentyone.app.entities.TypeVideo;
import com.twentyone.app.entities.User;
import com.twentyone.app.service.AnimeListService;
import com.twentyone.app.service.TypeVideoService;
import com.twentyone.app.service.UserService;

@RestController
@RequestMapping("/animelist")
public class AnimeListRestController {
	
	
	@Autowired
	AnimeListService animeListService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TypeVideoService typeVideoService;
	
	
	@GetMapping("")
	public ResponseEntity get(@RequestParam("category") Optional<String> category) {
		
		return ResponseEntity.ok(animeListService.findByCategory(category.orElse("%")));
	}
	
	@GetMapping("/{user}/{anime}")
	public ResponseEntity check(@PathVariable("user") String user, @PathVariable("anime") int anime) {
		return ResponseEntity.ok(animeListService.findByUserAndAnime(user, anime));
	}
	
	@GetMapping("/{user}")
	public ResponseEntity get1(@PathVariable("user") String user) {
		return ResponseEntity.ok(animeListService.findByUser(user));
	}
	
	@PostMapping("/modify")
	public ResponseEntity post(@RequestBody JsonNode animeList) {
		AnimeList anime = new AnimeList();
		Optional<User> u = userService.findById(animeList.get("user").asText());
		Optional<TypeVideo> tv = typeVideoService.findById(animeList.get("typeVideo").asInt());
		if(u.isPresent() && tv.isPresent()) {
			anime.setUser(u.get());
			anime.setStatus(animeList.get("status").asText());
			anime.setTypeVideo(tv.get());
			try {
				animeListService.insert(anime);
				return ResponseEntity.ok(anime);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity("Lỗi không xác định", HttpStatus.EXPECTATION_FAILED);
			}
		}
		return new ResponseEntity("User or anime are not found", HttpStatus.NO_CONTENT);
		
		
	}
	
	
	@PutMapping("/modify/{id}")
	public ResponseEntity put(@PathVariable("id") int id, @RequestBody JsonNode animeList) {
		Optional<AnimeList> animes = animeListService.findById(id);
		if(animes.isPresent()) {
			animes.get().setNote(animeList.get("note").asText());
			animes.get().setProcess(animeList.get("process").asInt());
			animes.get().setScore(animeList.get("score").asInt());
			animes.get().setStatus(animeList.get("status").asText());
			try {
				animeListService.update(animes.get());
				return ResponseEntity.ok(animes);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/modify/{id}/{user}")
	public ResponseEntity delete(@PathVariable("id") int id, @PathVariable("user") String userId) {
		System.out.println(id);
		System.out.println(userId);
		Optional<TypeVideo> typeVideo = typeVideoService.findById(id);
		Optional<User> user = userService.findById(userId);
		if(typeVideo.isPresent() && user.isPresent()) {
			List<AnimeList> animeList = animeListService.findByUserAndAnime(user.get().getUserName(), typeVideo.get().getId());
			if(!animeList.isEmpty()) {
				try {
					animeListService.delete(animeList.get(0));
					return ResponseEntity.ok(animeListService.findAll());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ResponseEntity("Lỗi không xác định", HttpStatus.EXPECTATION_FAILED);
				}
			}
		}
		return new ResponseEntity("Không tìm thấy anime", HttpStatus.BAD_REQUEST);
	}
}
