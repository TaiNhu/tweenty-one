package com.twentyone.app.service;

import java.util.List;

import com.twentyone.app.entities.AnimeList;

public interface AnimeListService extends BaseService<AnimeList, Integer>{

	public List<AnimeList> findByCategory(String category);
	
	public List<AnimeList> findByUser(String user);
	
	public List<AnimeList> findByUserAndAnime(String user, int anime);
}
