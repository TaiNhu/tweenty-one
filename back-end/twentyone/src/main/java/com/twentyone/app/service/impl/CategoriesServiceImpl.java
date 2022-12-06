package com.twentyone.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twentyone.app.dao.CategoriesDAO;
import com.twentyone.app.entities.Categories;
import com.twentyone.app.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService{

	@Autowired
	CategoriesDAO cDAO;
	
	@Override
	public void insert(Categories entity) {
		// TODO Auto-generated method stub
		cDAO.save(entity);
	}

	@Override
	public void update(Categories entity) {
		// TODO Auto-generated method stub
		cDAO.save(entity);
		
	}

	@Override
	public void delete(Categories entity) {
		// TODO Auto-generated method stub
		cDAO.delete(entity);
		
	}

	@Override
	public List<Categories> findAll() {
		// TODO Auto-generated method stub
		return cDAO.findAll();
	}

	@Override
	public Optional<Categories> findById(Integer key) {
		// TODO Auto-generated method stub
		return cDAO.findById(key);
	}
	

}
