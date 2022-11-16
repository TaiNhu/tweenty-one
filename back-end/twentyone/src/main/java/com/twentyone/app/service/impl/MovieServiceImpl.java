package com.twentyone.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twentyone.app.dao.MovieDAO;
import com.twentyone.app.entities.Movie;
import com.twentyone.app.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieDAO mDAO;

	@Override
	public void insert(Movie entity) {
		// TODO Auto-generated method stub
		mDAO.save(entity);
	}

	@Override
	public void update(Movie entity) {
		// TODO Auto-generated method stub
		mDAO.save(entity);
		
	}

	@Override
	public void delete(Movie entity) {
		// TODO Auto-generated method stub
		mDAO.delete(entity);
		
	}

	@Override
	public List<Movie> findAll() {
		// TODO Auto-generated method stub
		return mDAO.findAll();
	}

	@Override
	public Optional<Movie> findById(Integer key) {
		// TODO Auto-generated method stub
		return mDAO.findById(key);
	}

}
