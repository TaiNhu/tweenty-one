package com.twentyone.app.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<E, K> {

	public void insert(E entity) throws Exception;
	public void update(E entity) throws Exception;
	public void delete(E entity) throws Exception;
	public List<E> findAll();
	public Optional<E> findById(K key);
}
