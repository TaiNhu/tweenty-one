package com.twentyone.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.twentyone.app.entities.VideoGenres;

public interface VideoGenresDAO extends JpaRepository<VideoGenres, Integer>{

	
	@Query("select a from VideoGenres a where a.typeVideo.id = :typeVideoId")
	public List<VideoGenres> findByVideo(@Param("typeVideoId") int id);
	
	@Query("delete from VideoGenres")
	public void removeAll();
	
}
