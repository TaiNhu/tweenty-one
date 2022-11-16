package com.twentyone.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twentyone.app.dao.VideoGenresDAO;
import com.twentyone.app.entities.VideoGenres;
import com.twentyone.app.service.VideoGenresService;

@Service
public class VideoGenresServiceImpl implements VideoGenresService{
	
	
	@Autowired
	VideoGenresDAO vgDAO;

	@Override
	public void insert(VideoGenres entity) {
		// TODO Auto-generated method stub
		vgDAO.save(entity);
	}

	@Override
	public void update(VideoGenres entity) {
		// TODO Auto-generated method stub
		vgDAO.save(entity);
		
	}

	@Override
	public void delete(VideoGenres entity) {
		// TODO Auto-generated method stub
		vgDAO.delete(entity);
		
	}

	@Override
	public List<VideoGenres> findAll() {
		// TODO Auto-generated method stub
		return vgDAO.findAll();
	}

	@Override
	public Optional<VideoGenres> findById(Integer key) {
		// TODO Auto-generated method stub
		return vgDAO.findById(key);
	}
	
	

}
