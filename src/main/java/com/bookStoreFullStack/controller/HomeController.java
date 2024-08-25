package com.bookStoreFullStack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.Category;
import com.bookStoreFullStack.entity.OrderEntity;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.service.BookService;
import com.bookStoreFullStack.service.CategoryService;
import com.bookStoreFullStack.service.OrderEntityService;
import com.bookStoreFullStack.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private BookService bookService;
	@Autowired
	private CategoryService categpryService;
	@Autowired
	private HttpSession session;
	@Autowired
	private OrderEntityService orderEntityService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Book> books = bookService.getAllBooks();
		List<Category> categories = categpryService.getAllCategories();
		List<Book> booksReadMore = bookService.getBooksReadMore();
		List<Book> booksOnSale = bookService.getBooksOnSale();
		
		model.addAttribute("books", books);
		model.addAttribute("categories", categories);
		model.addAttribute("booksReadMore", booksReadMore);
		model.addAttribute("booksOnSale", booksOnSale);
		return "index";
	}
	
	@GetMapping("/home-admin")
	public String homeAdmin(Model model) {
		List<Book> books = bookService.getAllBooks();
		List<Category> categories = categpryService.getAllCategories();
		List<OrderEntity> orders = orderEntityService.getAllOrderEntity();
		List<User> users = userService.getAllUsers();
		int totalBooks = books.size();
		int totalOrders = orders.size();
		int totalUsers = users.size();
		int totalCategories = categories.size();
		
		model.addAttribute("books", books);
		model.addAttribute("categories", categories);
		model.addAttribute("orders", orders);
		model.addAttribute("users", users);
		model.addAttribute("totalBooks", totalBooks);
		model.addAttribute("totalOrders", totalOrders);
		model.addAttribute("totalUsers", totalUsers);
		model.addAttribute("totalCategories", totalCategories);
		
		return "admin/index"; 
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		return "about";
	}
	
	@GetMapping("/service")
	public String service(Model model) {
		return "services";
	}
	
	@GetMapping("/blog")
	public String blog(Model model) {
		return "blog";
	}
	
	@GetMapping("/contact")
	public String contact(Model model) {
		return "contact";
	}
	
	@GetMapping("/test")
	public String test(Model model) {
		return "test";
	}
}
