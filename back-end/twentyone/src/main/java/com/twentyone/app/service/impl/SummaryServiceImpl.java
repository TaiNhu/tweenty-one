package com.twentyone.app.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twentyone.app.service.SummaryService;

@Service
public class SummaryServiceImpl implements SummaryService{
	
	@Autowired
	EntityManager em;
	

	@Override
	public List<Object[]> top5View() {
		// TODO Auto-generated method stub
		String sql = "SELECT top 5 name, count FROM type_videos ORDER BY count DESC;";
		Query q = em.createNativeQuery(sql);
		return q.getResultList();
	}


	@Override
	public List<Object[]> top5Comment() {
		// TODO Auto-generated method stub
		String sql = "SELECT TOP 5 t.id, t.name, count(r.id) as c FROM type_videos t "
				+ "INNER JOIN reviews r ON r.movie_id = t.id "
				+ "group by t.id, t.name "
				+ "order by c DESC";
		Query q = em.createNativeQuery(sql);
		return q.getResultList();
	}


	@Override
	public List<Object[]> top5Follow() {
		// TODO Auto-generated method stub
		String sql = "select tv.id, tv.name, count(al.id) as count from type_videos tv "
				+ "inner join anime_list al on al.movie_id = tv.id "
				+ "group by tv.id, tv.name";
		Query q = em.createNativeQuery(sql);
		return q.getResultList();
	}


	@Override
	public List<Object[]> viewByCate() {
		// TODO Auto-generated method stub
		String sql = "SELECT c.id, c.name, sum(tv.count) as count FROM categories c "
				+ "LEFT JOIN video_genres vg ON vg.category_id = c.id "
				+ "LEFT JOIN type_videos tv ON tv.id = vg.movie_id "
				+ "GROUP BY c.id, c.name";
		Query q = em.createNativeQuery(sql);
		return q.getResultList();
	}

	
	
	

}
