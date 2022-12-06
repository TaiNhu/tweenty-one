package com.twentyone.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twentyone.app.dao.TypeDAO;
import com.twentyone.app.entities.Type;
import com.twentyone.app.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService{
	
	@Autowired
	TypeDAO tDAO;

	@Override
	public void insert(Type entity) {
		// TODO Auto-generated method stub
		tDAO.save(entity);
	}

	@Override
	public void update(Type entity) {
		// TODO Auto-generated method stub
		tDAO.save(entity);
		
	}

	@Override
	public void delete(Type entity) {
		// TODO Auto-generated method stub
		tDAO.delete(entity);
		
	}

	@Override
	public List<Type> findAll() {
		// TODO Auto-generated method stub
		return tDAO.findAll();
	}

	@Override
	public Optional<Type> findById(Integer key) {
		// TODO Auto-generated method stub
		return tDAO.findById(key);
	}
	
	

}
