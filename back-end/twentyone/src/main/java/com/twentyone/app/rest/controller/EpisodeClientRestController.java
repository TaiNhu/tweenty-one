package com.twentyone.app.rest.controller;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twentyone.app.entities.Episode;
import com.twentyone.app.service.EpisodeService;
import com.twentyone.app.service.impl.LoadVideoServiceImpl;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/episodes")
public class EpisodeClientRestController {
	
	@Autowired
	EpisodeService episodeService;
	
	@Autowired
	LoadVideoServiceImpl stream;
	
	@GetMapping(value = "/{id}", produces = "video/mp4")
	public Mono<Resource> get(@PathVariable("id") int id, @RequestHeader("Range") String range) {
		Optional<Episode> ep = episodeService.findById(id);
		if(ep.isPresent()) {
			if(ep.get().getLink().contains("http")) {
				return null;
			}
			return (Mono<Resource>)stream.getVideo(ep.get());
		}
		return null;
	}
	
	@GetMapping("/normal/{id}")
	public ResponseEntity getVideo(@PathVariable("id") int id) {
		Optional<Episode> ep = episodeService.findById(id);
		if(ep.isPresent()) {
			if(ep.get().getLink().contains("http")) {
				System.out.println("[VAO VAO VAO VA OVA VOA VOAV OAV OAV OA VA OVA O]");
				System.out.println(ep.get().getLink());
				Map response = new HashMap();
				response.put("response", ep.get().getLink());
				return ResponseEntity.ok(response);
			}
		}
		System.out.println("[FDAFLKAJFOAIUWOQJRLJKDSOAIUDWQJDWLQDWQOIDUSALJD]");
		return ResponseEntity.badRequest().build();
	}
	
}
