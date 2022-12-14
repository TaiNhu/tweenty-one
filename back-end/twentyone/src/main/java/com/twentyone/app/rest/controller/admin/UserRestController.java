package com.twentyone.app.rest.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.twentyone.app.entities.User;
import com.twentyone.app.service.UserService;

@RestController
@RequestMapping("/admin")
public class UserRestController {
	
	
	@Autowired
	UserService userService;

	@GetMapping("/users")
	public ResponseEntity get() {
		List<User> users = userService.findAll();
		return new ResponseEntity("Thành công", HttpStatus.OK).ok(users);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity update(@PathVariable("id") String id, @RequestBody User userUpdate) {
		Optional<User> user = userService.findById(id);
		if(user.isPresent()) {
			user.get().setEmail(userUpdate.getEmail());
			if(user.get().getImage() != null) {
				user.get().setImage(userUpdate.getImage());
			}
			user.get().setPassword(userUpdate.getPassword());
			try {
				userService.update(user.get());
				ResponseEntity.ok(user.get());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity("Lỗi không xác định", HttpStatus.EXPECTATION_FAILED);
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/users")
	public ResponseEntity insert(@RequestBody User user) {
		try {
			userService.insert(user);
			return new ResponseEntity("Thành công", HttpStatus.OK).ok(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity("Lỗi không xác định", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity insert(@PathVariable String id) {
		try {
			Optional<User> user = userService.findById(id);
			if(user.isPresent()) {
				userService.delete(user.get());
				Map response = new HashMap();
				response.put("message", "Thanh Cong");
				return ResponseEntity.ok(response);
			}
			return new ResponseEntity("Sai trường", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity("Lỗi không xác định", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
