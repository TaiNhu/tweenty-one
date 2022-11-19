package com.twentyone.app.service;

import java.util.List;

import com.twentyone.app.entities.TypeVideo;

public interface TypeVideoService extends BaseService<TypeVideo, Integer>{
	
	public List<TypeVideo> findByName(String name);
	public List<TypeVideo> findSameAnother(int id);
	public List<TypeVideo> findAllDESC();
	
	public List<TypeVideo> filter(int[] categories, String[] sort);
	public List<TypeVideo> typeFilter(int[] types, String[] sort);
}
