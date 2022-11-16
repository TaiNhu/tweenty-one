package com.twentyone.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twentyone.app.dao.TypeVideoDAO;
import com.twentyone.app.entities.TypeVideo;
import com.twentyone.app.service.TypeVideoService;

@Service
public class TypeVideoServiceImpl implements TypeVideoService{
	
	@Autowired
	TypeVideoDAO tvDAO;

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
	
	

}
