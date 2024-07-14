package com.bookStoreFullStack.controller;

import java.awt.print.Pageable;
import java.util.List;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.Category;
import com.bookStoreFullStack.service.BookService;
import com.bookStoreFullStack.service.CategoryService;
import com.bookStoreFullStack.service.LikeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private HttpSession session;
	@Autowired
    private LikeService likeService;
	
	@GetMapping("/book-filter")
	public String bookFilter(Model model) {
		List<Book> books = bookService.getAllBooks();
		List<Category> categories = categoryService.getAllCategories();
		
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
