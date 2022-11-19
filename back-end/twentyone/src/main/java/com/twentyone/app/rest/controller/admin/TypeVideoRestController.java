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

import com.twentyone.app.entities.TypeVideo;
import com.twentyone.app.service.TypeVideoService;

@RestController
@RequestMapping("/admin")
public class TypeVideoRestController {

	
	@Autowired
	TypeVideoService typeVideoService;

	@GetMapping("/typevideos")
	public ResponseEntity get() {
		List<TypeVideo> typeVideo = typeVideoService.findAll();
		return ResponseEntity.ok(typeVideo);
	}
	
	@PostMapping("/typevideos")
	public ResponseEntity insert(@RequestBody TypeVideo typeVideo) {
		try {
			typeVideoService.insert(typeVideo);
			return new ResponseEntity("Insert thành công", HttpStatus.OK).ok(typeVideo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity("Lỗi insert", HttpStatus.OK);
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
