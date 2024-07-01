package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.Author;

public interface AuthorService {
	
	List<Author> getAllAuthors();
	
	Author saveAuthor(Author Author);
	
	Author getAuthorById(int id);
	
	Author updateAuthor(Author Author);
	
	void deleteAuthor(int id);
}
