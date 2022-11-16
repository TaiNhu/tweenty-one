package com.twentyone.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twentyone.app.entities.Categories;

public interface CategoriesDAO extends JpaRepository<Categories, Integer>{

}
