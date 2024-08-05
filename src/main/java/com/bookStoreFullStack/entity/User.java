package com.bookStoreFullStack.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "role")
	private int role;
	
	@OneToMany(mappedBy = "user")
    private List<LikeRating> likes;
	
	
	@OneToMany(mappedBy = "user")
	private List<Rating> ratings;
	
}