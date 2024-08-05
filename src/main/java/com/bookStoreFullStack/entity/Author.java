
package com.bookStoreFullStack.entity;

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
@Table(name = "author")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "home_town")
	private String homeTown;
	
	@Column(name = "yearOfBirth")
	private int yearOfBirth;
	
	@Column(name = "story")
	private String story;
	
	@OneToMany(mappedBy = "author")
    private List<Book> books;
	
}
