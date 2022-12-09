package com.twentyone.app.rest.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twentyone.app.service.SummaryService;

@RestController
@RequestMapping("/admin")
public class SummaryRestController {
	
	
	@Autowired
	SummaryService summaryService;

	@GetMapping("/views")
	public ResponseEntity views() {
		return ResponseEntity.ok(summaryService.top5View());
	}
	
	@GetMapping("/comment")
	public ResponseEntity comment() {
		return ResponseEntity.ok(summaryService.top5Comment());
	}
	
	@GetMapping("/follow")
	public ResponseEntity follow() {
		return ResponseEntity.ok(summaryService.top5Follow());
	}
	
	
	@GetMapping("/pie")
	public ResponseEntity pie() {
		return ResponseEntity.ok(summaryService.viewByCate());
	}
	
}
