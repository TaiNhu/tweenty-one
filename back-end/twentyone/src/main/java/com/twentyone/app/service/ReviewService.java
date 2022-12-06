package com.twentyone.app.service;

import java.util.List;

import com.twentyone.app.entities.Review;

public interface ReviewService extends BaseService<Review, Integer>{
	public List<Review> findByVideoId(int id);
}
