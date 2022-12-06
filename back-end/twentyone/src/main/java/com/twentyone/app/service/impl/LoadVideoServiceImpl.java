package com.twentyone.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoadVideoServiceImpl {

	public static final String FORMAT = "classpath:films/%s";
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	public Mono<Resource> getVideo(String title) {
		return Mono.fromSupplier(() -> resourceLoader.
				getResource(String.format(FORMAT, title)));
	}
	
}
