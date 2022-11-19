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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twentyone.app.entities.Episode;
import com.twentyone.app.service.EpisodeService;

@RestController
@RequestMapping("/admin")
public class EpisodeRestController {
	
	
	@Autowired
	EpisodeService episodeService;

	@GetMapping("/episodes")
	public ResponseEntity get() {
		List<Episode> episodes = episodeService.findAll();
		return new ResponseEntity("Thành công", HttpStatus.OK).ok(episodes);
	}
	
	@PostMapping("/episodes")
	public ResponseEntity insert(@RequestBody Episode episode) {
		try {
			episodeService.insert(episode);
			return new ResponseEntity("Thành công", HttpStatus.OK).ok(episode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity("Lỗi không xác định", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@DeleteMapping("/episodes/{id}")
	public ResponseEntity insert(@PathVariable int id) {
		try {
			Optional<Episode> episode = episodeService.findById(id);
			if(episode.isPresent()) {
				episodeService.delete(episode.get());
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
