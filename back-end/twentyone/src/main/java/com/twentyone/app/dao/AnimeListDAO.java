package com.twentyone.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twentyone.app.entities.AnimeList;

public interface AnimeListDAO extends JpaRepository<AnimeList, Integer>{

}
