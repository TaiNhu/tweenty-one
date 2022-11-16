package com.twentyone.app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "users")

public class User {
	
	@Id
	String userName;
	String password;
	String image;
	String email;
	String role;
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<AnimeList> animeList;
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<Review> review;
 	
}
