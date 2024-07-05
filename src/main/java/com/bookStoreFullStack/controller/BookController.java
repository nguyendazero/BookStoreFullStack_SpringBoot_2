package com.bookStoreFullStack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.Category;
import com.bookStoreFullStack.service.BookService;
import com.bookStoreFullStack.service.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private CategoryService categpryService;
	@Autowired
	private HttpSession session;
	
	@GetMapping("/book-filter")
	public String bookFilter(Model model) {
		List<Book> books = bookService.getAllBooks();
		List<Category> categories = categpryService.getAllCategories();
		
		model.addAttribute("books", books);
		model.addAttribute("categories", categories);
		return "book-filter";
	}
	
	@GetMapping("/book/{id}")
	public String bookDetail(Model model, @PathVariable int id) {
		Book book = bookService.getBookById(id);
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("books", books);
		model.addAttribute("book", book);
		
		
		return "book-detail";
	}
}
