package com.twentyone.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twentyone.app.dao.ReviewDAO;
import com.twentyone.app.entities.Review;
import com.twentyone.app.service.ReviewService;


@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewDAO rDAO;
	
	@Override
	public void insert(Review entity) {
		// TODO Auto-generated method stub
		rDAO.save(entity);
	}

	@Override
	public void update(Review entity) {
		// TODO Auto-generated method stub
		rDAO.save(entity);
		
	}

	@Override
	public void delete(Review entity) {
		// TODO Auto-generated method stub
		rDAO.delete(entity);
		
	}

	@Override
	public List<Review> findAll() {
		// TODO Auto-generated method stub
		return rDAO.findAll();
	}

	@Override
	public Optional<Review> findById(Integer key) {
		// TODO Auto-generated method stub
		return rDAO.findById(key);
	}

	@Override
	public List<Review> findByVideoId(int id) {
		// TODO Auto-generated method stub
		return rDAO.findByVideoId(id);
	}

}
