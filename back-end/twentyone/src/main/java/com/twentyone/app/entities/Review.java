package com.twentyone.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "reviews")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String content;
	Boolean recommend;
	@ManyToOne
	@JoinColumn(name = "movie_id")
	TypeVideo typeVideo;
	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;
}
