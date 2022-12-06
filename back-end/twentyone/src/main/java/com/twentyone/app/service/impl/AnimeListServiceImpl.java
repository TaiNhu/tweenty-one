package com.twentyone.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twentyone.app.dao.AnimeListDAO;
import com.twentyone.app.entities.AnimeList;
import com.twentyone.app.service.AnimeListService;

@Service
public class AnimeListServiceImpl implements AnimeListService{
	
	
	@Autowired
	AnimeListDAO alDAO;

	@Override
	public void insert(AnimeList entity) {
		alDAO.save(entity);
	}

	@Override
	public void update(AnimeList entity) {
		// TODO Auto-generated method stub
		alDAO.save(entity);
	}

	@Override
	public void delete(AnimeList entity) {
		// TODO Auto-generated method stub
		alDAO.delete(entity);
	}

	@Override
	public List<AnimeList> findAll() {
		// TODO Auto-generated method stub
		return alDAO.findAll();
	}

	@Override
	public Optional<AnimeList> findById(Integer key) {
		// TODO Auto-generated method stub
		return alDAO.findById(key);
	}

	@Override
	public List<AnimeList> findByCategory(String category) {
		// TODO Auto-generated method stub
		return alDAO.findByCategory(category);
	}

	@Override
	public List<AnimeList> findByUser(String user) {
		// TODO Auto-generated method stub
		return alDAO.findByUser(user);
	}

	@Override
	public List<AnimeList> findByUserAndAnime(String user, int anime) {
		// TODO Auto-generated method stub
		return alDAO.findByUserAndAnime(user, anime);
	}

}
