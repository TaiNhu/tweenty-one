package com.twentyone.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twentyone.app.entities.Movie;

public interface MovieDAO extends JpaRepository<Movie, Integer>{

}
