package com.twentyone.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twentyone.app.dao.EpisodeDAO;
import com.twentyone.app.entities.Episode;
import com.twentyone.app.service.EpisodeService;

@Service
public class EpisodeServiceImpl implements EpisodeService{

	@Autowired
	EpisodeDAO eDAO;
	
	@Override
	public void insert(Episode entity) {
		// TODO Auto-generated method stub
		eDAO.save(entity);
	}

	@Override
	public void update(Episode entity) {
		// TODO Auto-generated method stub
		eDAO.save(entity);
		
	}

	@Override
	public void delete(Episode entity) {
		// TODO Auto-generated method stub
		eDAO.delete(entity);
		
	}

	@Override
	public List<Episode> findAll() {
		// TODO Auto-generated method stub
		return eDAO.findAll();
	}

	@Override
	public Optional<Episode> findById(Integer key) {
		// TODO Auto-generated method stub
		return eDAO.findById(key);
	}

}
