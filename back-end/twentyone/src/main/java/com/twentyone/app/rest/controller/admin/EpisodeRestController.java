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
import com.twentyone.app.entities.TypeVideo;
import com.twentyone.app.service.EpisodeService;
import com.twentyone.app.service.TypeVideoService;

@RestController
@RequestMapping("/admin")
public class EpisodeRestController {
	
	
	@Autowired
	EpisodeService episodeService;
	@Autowired
    TypeVideoService typeVideoService;

	@GetMapping("/episodes")
	public ResponseEntity get() {
		List<Episode> episodes = episodeService.findAll();
		return new ResponseEntity("Thành công", HttpStatus.OK).ok(episodes);
	}
	@GetMapping("/episodes/{id}")
	public ResponseEntity getOne(@PathVariable("id") int id) {
		Optional<Episode> episodes = episodeService.findById(id);
		return ResponseEntity.ok(episodes);
	}
	
	@PostMapping("/episodes")
	public ResponseEntity insert(@RequestBody JsonNode episodeJSON) {
        Optional<TypeVideo> typeVideo = typeVideoService.findById(episodeJSON.get("typeVideo").asInt());
        if(typeVideo.isPresent()) {
            try {
                Episode episode = new Episode();
                if(episodeJSON.get("id") != null) {
                	episode.setId(episodeJSON.get("id").asInt());
                }
                if(episodeJSON.get("episodeNumber") != null) {
                    episode.setEpisodeNumber(episodeJSON.get("episodeNumber").asInt());
                }
                if (episodeJSON.get("link") != null) {
                    episode.setLink(episodeJSON.get("link").asText());
                }
                if(episodeJSON.get("name") != null) {
                    episode.setName(episodeJSON.get("name").asText());
                }
                episode.setTypeVideo(typeVideo.get());
                episodeService.insert(episode);
                return new ResponseEntity("Thành công", HttpStatus.OK).ok(episode);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return new ResponseEntity("Lỗi không xác định", HttpStatus.EXPECTATION_FAILED);
            }
        }
        return ResponseEntity.badRequest().build();

    }

	
	@PutMapping("/episodes/{id}")
	public ResponseEntity update(@PathVariable("id") int id, @RequestBody JsonNode episodeUpdate) {
		Optional<Episode> episode = episodeService.findById(id);
		ObjectMapper mapper = new ObjectMapper();
		TypeVideo typeVideo = (TypeVideo) mapper.convertValue(episodeUpdate.get("typeVideo"), TypeVideo.class);
		if(episode.isPresent()) {
			episode.get().setEpisodeNumber(episodeUpdate.get("episodeNumber").asInt());
			episode.get().setLink(episodeUpdate.get("link").asText());
			episode.get().setName(episodeUpdate.get("name").asText());
			episode.get().setTypeVideo(typeVideo);
			try {
				episodeService.update(episode.get());
				return ResponseEntity.ok(episode.get());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity("Lỗi không xác định", HttpStatus.EXPECTATION_FAILED);
			}
		}
		return ResponseEntity.badRequest().build();
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
