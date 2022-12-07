package com.twentyone.app.service.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Service;

import com.twentyone.app.dao.VideoGenresDAO;
import com.twentyone.app.entities.VideoGenres;
import com.twentyone.app.service.VideoGenresService;

import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
@Persistent
public class VideoGenresServiceImpl implements VideoGenresService{
	
	
	@Autowired
	VideoGenresDAO vgDAO;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insert(VideoGenres entity) {
		// TODO Auto-generated method stub
		vgDAO.save(entity);
	}

	@Override
	public void update(VideoGenres entity) {
		// TODO Auto-generated method stub
		vgDAO.save(entity);
		
	}

	@Override
	public void delete(VideoGenres entity) {
		// TODO Auto-generated method stub
		vgDAO.delete(entity);
		
	}

	@Override
	public List<VideoGenres> findAll() {
		// TODO Auto-generated method stub
		return vgDAO.findAll();
	}

	@Override
	public Optional<VideoGenres> findById(Integer key) {
		// TODO Auto-generated method stub
		return vgDAO.findById(key);
	}

	@Override
	public List<VideoGenres> findByVideo(int id) {
		// TODO Auto-generated method stub
		return vgDAO.findByVideo(id);
	}

	@Override
	@Transactional
	public void removeAll(int id) {
		// TODO Auto-generated method stub
		try {
			Query q = entityManager.createNativeQuery("DELETE FROM video_genres where movie_id = :id");
			q.setParameter("id", id);
			q.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
