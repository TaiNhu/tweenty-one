package com.twentyone.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twentyone.app.entities.Episode;

public interface EpisodeDAO extends JpaRepository<Episode, Integer>{

}
