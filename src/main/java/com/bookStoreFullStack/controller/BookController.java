package com.bookStoreFullStack.controller;


import java.util.List;

import java.text.DecimalFormat;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookStoreFullStack.entity.Author;
import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.Category;
import com.bookStoreFullStack.entity.LikeRating;
import com.bookStoreFullStack.entity.Rating;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.service.AuthorService;
import com.bookStoreFullStack.service.BookService;
import com.bookStoreFullStack.service.CategoryService;
import com.bookStoreFullStack.service.LikeRatingService;
import com.bookStoreFullStack.service.RatingService;

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
    private LikeRatingService likeService;
	@Autowired
	private RatingService ratingService;
	@Autowired
	private AuthorService authorService;
	
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
		User userLogin = (User) session.getAttribute("userLogin");
		
		Book book = bookService.getBookById(id);
		List<Book> books = bookService.getAllBooks();
		List<Rating> ratings = ratingService.getRatingsByBookId(id);
		DecimalFormat df = new DecimalFormat("#.#");
        String formattedStars = df.format(book.getAverageStars());
        
        for (Rating rating : book.getRatings()) {
            List<LikeRating> likes = likeService.findLikesByRatingId(rating.getId());
            rating.setLikes(likes);
            LikeRating existingLike = likeService.getLikeRatingByUserAndRating(userLogin, rating);
            if(existingLike != null) {
            	model.addAttribute("true", existingLike != null);
            }
        }
        
        
        
        double averageStars = ratingService.calculateAverageStars(id);
	    book.setAverageStars(averageStars);
	    bookService.saveBook(book);
		
        model.addAttribute("formattedAverageStars", formattedStars);
        model.addAttribute("ratings", ratings);
		model.addAttribute("books", books);
		model.addAttribute("book", book);
		
		return "book-detail";
	}
	
	@GetMapping("/book/search")
    public String searchBooks(@RequestParam("query") String query, Model model) {
        List<Book> books = bookService.searchBooks(query);
        List<Category> categories = categoryService.getAllCategories();
		
        model.addAttribute("query", query);
		model.addAttribute("categories", categories);
        model.addAttribute("books", books);
        return "book-filter"; 
    }
	
	@GetMapping("/book/category/{id}")
	public String showBooksByCate(@PathVariable("id") int id, Model model){
		List<Book> books = bookService.getBooksByCategory(id);
		List<Category> categories = categoryService.getAllCategories();
		
		model.addAttribute("categories", categories);
		 model.addAttribute("books", books);
		return "book-filter";
	}
	
	 @GetMapping("/book/searchByPrice")
	    public String showBooksByPriceRange(
	            @RequestParam("minPrice") Double minPrice,
	            @RequestParam("maxPrice") Double maxPrice,
	            Model model) {
	        
	        List<Book> books = bookService.searchBooksByPriceRange(minPrice, maxPrice);
	        List<Category> categories = categoryService.getAllCategories();
			
			model.addAttribute("categories", categories);
	        model.addAttribute("books", books);
	        model.addAttribute("minPrice", minPrice);
	        model.addAttribute("maxPrice", maxPrice);

	        return "book-filter";
	    }
	
	 @GetMapping("/book/status/status")
	 public String showBookByStatus(@RequestParam(name = "name") String name, Model model) {
	     List<Book> books = bookService.getBookStatus(name);
	     List<Category> categories = categoryService.getAllCategories();
			
		 model.addAttribute("categories", categories);
	     model.addAttribute("books", books);
	     return "book-filter";
	 }
	 
	 
	 /*****************************ADMIN*********************************/
	 
	 @GetMapping("/admin/book")
	 public String BookManager(Model model) {
		 List<Book> books = bookService.getAllBooks();
		 List<Category> categories = categoryService.getAllCategories();
		 List<Author> authors = authorService.getAllAuthors();
		 List<String> statuses = List.of("runout", "onsale", "discount");
			
		 model.addAttribute("books", books);
		 model.addAttribute("categories", categories);
		 model.addAttribute("authors", authors);
		 model.addAttribute("statuses", statuses);
		 return "admin/book";
	 }
	 
	 @GetMapping("/admin/book/add")
	 public String addBook(Model model) {
		 return "admin/add-book";
	 }
	 
	 @PostMapping("/admin/book/update")
	 public String updateBook(@ModelAttribute("book") Book updateBook) {
		 Book b = bookService.getBookById(updateBook.getId());
		 updateBook.setAverageStars(b.getAverageStars());
		 bookService.saveBook(updateBook);
		 return "redirect:/admin/book";
	 }
	 
	 @GetMapping("/admin/books/delete/{id}")
	 public String deleteBook(@PathVariable("id") int id) {
		 bookService.deleteBook(id);
		 return "redirect:/admin/book";
	 }
	 
	 
	 
	 
	 
}
