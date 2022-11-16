package com.twentyone.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twentyone.app.entities.VideoGenres;

public interface VideoGenresDAO extends JpaRepository<VideoGenres, Integer>{

}
