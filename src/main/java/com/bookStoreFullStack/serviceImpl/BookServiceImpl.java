package com.bookStoreFullStack.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.repository.BookRepository;
import com.bookStoreFullStack.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> getBooksByCategory(int categoryId) {
	    return bookRepository.findByCategoryId(categoryId);
	}

	@Override
	public List<Book> getBooksByAuthor(int authorId) {
	    return bookRepository.findByAuthorId(authorId);
	}

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book getBookById(int id) {
		return bookRepository.findById(id).get();
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}
	
	@Override
	public void deleteBook(int id) {
		bookRepository.deleteById(id);
	}

	@Override
	public List<Book> getBooksReadMore() {
		return bookRepository.findByStatus("read more");
	}

	@Override
	public List<Book> getBooksOnSale() {
		return bookRepository.findByStatus("on sale");
	}

	@Override
	public List<Book> searchBooks(String query) {
        return bookRepository.findByNameContainingIgnoreCase(query);
    }

	@Override
	public List<Book> searchBooksByPriceRange(Double minPrice, Double maxPrice) {
        return bookRepository.findByPriceBetween(minPrice, maxPrice);
    }

	@Override
	public List<Book> getBookStatus(String status) {
		return bookRepository.findByStatus(status);
	}

}
