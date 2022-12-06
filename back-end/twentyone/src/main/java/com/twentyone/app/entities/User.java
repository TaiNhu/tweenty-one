package com.twentyone.app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private String userName;
	private String password;
	private String image = "user.png";
	private String email;
	private String role = "USER";
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<AnimeList> animeList;
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Review> review;
 	
}
