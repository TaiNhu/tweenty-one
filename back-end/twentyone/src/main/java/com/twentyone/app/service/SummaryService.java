package com.twentyone.app.service;

import java.util.List;

public interface SummaryService {

	public List<Object[]> top5View();
	
	public List<Object[]> top5Comment();
	
	public List<Object[]> top5Follow();
	
	public List<Object[]> viewByCate();
}
