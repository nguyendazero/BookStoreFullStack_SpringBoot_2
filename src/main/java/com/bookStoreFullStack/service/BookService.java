package com.bookStoreFullStack.service;

import java.awt.print.Pageable;
import java.util.List;

import org.hibernate.query.Page;

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
	
	List<Book> getBooksReadMore();
	
	List<Book> getBooksOnSale();
	
}
