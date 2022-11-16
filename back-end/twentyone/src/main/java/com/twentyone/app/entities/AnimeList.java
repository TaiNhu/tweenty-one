package com.twentyone.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.Data;

@Data
@Entity
@Table(name = "anime_list")
public class AnimeList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int score;
	int process;
	String note;
	String status;
	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;
	@ManyToOne
	@JoinColumn(name = "movie_id")
	TypeVideo typeVideo;
	
}
