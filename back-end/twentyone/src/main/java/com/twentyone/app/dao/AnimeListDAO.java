package com.twentyone.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.twentyone.app.entities.AnimeList;

public interface AnimeListDAO extends JpaRepository<AnimeList, Integer>{
	
	@Query("select a from AnimeList a where a.status like :category")
	public List<AnimeList> findByCategory(@Param("category") String category);

	@Query("select a from AnimeList a where a.user.userName like :user")
	public List<AnimeList> findByUser(@Param("user") String user);
	
	@Query("select a from AnimeList a where a.user.userName like :user and a.typeVideo.id = :anime")
	public List<AnimeList> findByUserAndAnime(@Param("user") String user, @Param("anime") int anime);
	
}
