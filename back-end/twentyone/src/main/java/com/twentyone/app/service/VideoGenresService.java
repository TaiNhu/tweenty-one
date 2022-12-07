package com.twentyone.app.service;

import java.util.List;

import com.twentyone.app.entities.VideoGenres;

public interface VideoGenresService extends BaseService<VideoGenres, Integer>{

	public List<VideoGenres> findByVideo(int id);

	public void removeAll(int id);
	
}
