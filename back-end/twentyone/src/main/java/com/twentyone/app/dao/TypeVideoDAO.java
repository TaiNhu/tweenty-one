package com.twentyone.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.twentyone.app.entities.TypeVideo;

public interface TypeVideoDAO extends JpaRepository<TypeVideo, Integer>{

	@Query("SELECT f from TypeVideo f where f.movie.name like %:name%")
	public List<TypeVideo> findByName(@Param("name") String name);
	
	
	@Query("SELECT f from TypeVideo f where f.movie.id = (SELECT f.movie.id from TypeVideo f where f.id = :id) and f.id <> :id")
	public List<TypeVideo> findSameAnother(int id);
	
	
	@Query("SELECT f from TypeVideo f order by f.id desc")
	public List<TypeVideo> findAllDESC();
	
	@Query(value = "select top 6 f.* from type_videos f "
			+ "inner join reviews r on r.movie_id = f.id "
			+ "group by f.id, f.count, f.description, "
			+ "f.image, f.movie_id, f.name, f.type_id, f.type_name "
			+ "order by max(r.id) desc", nativeQuery = true)
	public List<TypeVideo> findByReviewASC();

}
