package com.bookStoreFullStack.service;

import java.util.List;



import com.bookStoreFullStack.entity.Book;

public interface BookService {
	List<Book> getAllBooks();
	
	List<Book> getBooksByCategory(int id);
	
	List<Book> getBooksByAuthor(int id);
	
	List<Book> getBookStatus(String status);
	
	Book saveBook(Book Book);
	
	Book getBookById(int id);
	
	Book updateBook(Book Book);
	
	void deleteBook(int id);
	
	List<Book> getBooksReadMore();
	
	List<Book> getBooksOnSale();
	
	List<Book> searchBooks(String query);
	
	List<Book> searchBooksByPriceRange(Double minPrice, Double maxPrice);
	
}
