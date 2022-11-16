package com.twentyone.app.entities;

import javax.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
@Table(name = "video_genres")
public class VideoGenres {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@ManyToOne
	@JoinColumn(name = "movie_id")
	TypeVideo typeVideo;
	@ManyToOne
	@JoinColumn(name = "category_id")
	Categories category;
}
