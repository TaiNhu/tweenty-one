package com.twentyone.app.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "type_videos")
public class TypeVideo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String image;
	String description;
	int count = 0;
	@ManyToOne
	@JoinColumn(name = "movie_id")
	Movie movie;
	@ManyToOne
	@JoinColumn(name = "type_id")
	Type type;
	@OneToMany(mappedBy = "typeVideo")
	List<Episode> episodes;
	@OneToMany(mappedBy = "typeVideo")
	List<VideoGenres> videoGenres;
}
