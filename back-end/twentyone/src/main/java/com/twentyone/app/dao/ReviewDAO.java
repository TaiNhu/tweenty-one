package com.twentyone.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.twentyone.app.entities.Review;

public interface ReviewDAO extends JpaRepository<Review, Integer>{
	
	@Query("SELECT r from Review r where r.typeVideo.id = :id")
	public List<Review> findByVideoId(@Param("id") int id);
	
}
