package com.twentyone.app.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.twentyone.app.dao.TypeVideoDAO;
import com.twentyone.app.entities.TypeVideo;
import com.twentyone.app.service.TypeVideoService;

@Service
@Repository
public class TypeVideoServiceImpl implements TypeVideoService{
	
	@Autowired
	TypeVideoDAO tvDAO;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public void insert(TypeVideo entity) {
		// TODO Auto-generated method stub
		tvDAO.save(entity);
	}

	@Override
	public void update(TypeVideo entity) {
		// TODO Auto-generated method stub
		tvDAO.save(entity);
		
	}

	@Override
	public void delete(TypeVideo entity) {
		// TODO Auto-generated method stub
		tvDAO.delete(entity);
		
	}

	@Override
	public List<TypeVideo> findAll() {
		// TODO Auto-generated method stub
		return tvDAO.findAll();
	}

	@Override
	public Optional<TypeVideo> findById(Integer key) {
		// TODO Auto-generated method stub
		return tvDAO.findById(key);
	}

	@Override
	public List<TypeVideo> findByName(String name) {
		// TODO Auto-generated method stub
		return tvDAO.findByName(name);
	}

	@Override
	public List<TypeVideo> findSameAnother(int id) {
		// TODO Auto-generated method stub
		String query = "select f.id, f.name, f.description, f.image, f.movie_id, f.type_id, f.count, f.type_name from type_videos f "
				+ "inner join movies m on m.id = f.movie_id "
				+ "where f.movie_id = (select movie_id "
				+ "from type_videos f "
				+ "inner join movies m on m.id = f.movie_id "
				+ "where f.id = " + id + ") "
				+ "and f.id <> " + id;
		return entityManager.createNativeQuery(query, TypeVideo.class).getResultList();
	}

	@Override
	public List<TypeVideo> findAllDESC() {
		// TODO Auto-generated method stub
		return tvDAO.findAllDESC();
	}
	
	public List<TypeVideo> filter(int[] categories, Optional<String> name, String[] sort){
		String query = "select distinct f.id, f.name, f.description, f.image, f.movie_id, f.type_id, f.count, f.type_name from type_videos f "
				+ "inner join video_genres v on v.movie_id = f.id";
		Boolean a = true;
		for(int category : categories) {
			if(a) {
				query += " where v.category_id =" + category;
				a = false;
			} else {
				query += " or v.category_id = " + category;
				
			}
		}
		if(name.isPresent()) {
			if(!a) {
				query += " and ";
			} else {
				query += " where ";
			}
			query += " f.name like '%" + name.get() + "%'";
		}
		query += " order by f."+ sort[0] +" " + sort[1];
		return entityManager.createNativeQuery(query, TypeVideo.class).getResultList();
	}
	
	public List<TypeVideo> typeFilter(int[] types, String[] sort){
		String query = "select distinct f.id, f.name, f.description, f.image, f.movie_id, f.type_id, f.count, f.type_name from type_videos f ";
		Boolean a = true;
		for(int type : types) {
			if(a) {
				query += " where f.type_id =" + type;
				a = false;
			} else {
				query += " or f.type_id = " + type;
				
			}
		}
		query += " order by f."+ sort[0] +" " + sort[1];
		return entityManager.createNativeQuery(query, TypeVideo.class).getResultList();
	}
	
	public List getAvgScore(int id) {
		String query = "select avg(score) from anime_list a "
				+ "group by a.movie_id "
				+ "having a.movie_id = " + id;
		return entityManager.createNativeQuery(query).getResultList();
	}

	@Override
	public List<TypeVideo> findByReviewASC() {
		// TODO Auto-generated method stub
		return tvDAO.findByReviewASC();
	}
	

}
