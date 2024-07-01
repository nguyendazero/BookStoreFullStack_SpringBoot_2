package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.Book;

public interface BookService {
	List<Book> getAllBooks();
	
	List<Book> getBooksByCategory(int id);
	
	List<Book> getBooksByAuthor(int id);
	
	List<Book> getBookBySearchName(String key);
	
	Book saveBook(Book Book);
	
	Book getBookById(int id);
	
	Book updateBook(Book Book);
	
	void deleteBook(int id);
}
