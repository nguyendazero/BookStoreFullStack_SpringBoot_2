package com.bookStoreFullStack.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.LikeRating;
import com.bookStoreFullStack.entity.Rating;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.service.BookService;
import com.bookStoreFullStack.service.LikeRatingService;
import com.bookStoreFullStack.service.OrderEntityService;
import com.bookStoreFullStack.service.RatingService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RatingController {
	@Autowired
	private RatingService ratingService;
	@Autowired
	private BookService bookService;
	@Autowired
	private HttpSession session;
	@Autowired
	private OrderEntityService orderEntityService;
	@Autowired
	private LikeRatingService likeService;
	
	@PostMapping("/rating/add/{id}")
	public String addRating(@RequestParam("bookId") int bookId, 
	                        @RequestParam("stars") int stars, 
	                        @RequestParam("content") String content, 
	                        HttpSession session, 
	                        RedirectAttributes redirectAttributes) {
	    
	    User userLogin = (User) session.getAttribute("userLogin");
	    if (userLogin == null) {
	        return "redirect:/user/login-page";
	    }

	    Book book = bookService.getBookById(bookId);
	    
	    boolean hasPurchased = orderEntityService.hasUserPurchasedBook(userLogin, bookId);
	    if (!hasPurchased) {
	        redirectAttributes.addFlashAttribute("error", "Bạn chưa mua sản phẩm này!");
	        return "redirect:/book/" + bookId;
	    }

	    Rating rating = new Rating();
	    rating.setUser(userLogin); 
	    rating.setBook(book);
	    rating.setStars(stars);
	    rating.setContent(content);
	    rating.setDate(new Date(System.currentTimeMillis()));

	    ratingService.saveRating(rating);

	    // Cập nhật điểm trung bình của book
	    double averageStars = ratingService.calculateAverageStars(bookId);
	    book.setAverageStars(averageStars);
	    bookService.saveBook(book);

	    return "redirect:/book/" + bookId;
	}
	
	@PostMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") int reviewId, RedirectAttributes redirectAttributes) {
		User userLogin = (User) session.getAttribute("userLogin");
		if(userLogin == null) {
			return "redirect:/user/login-page";
		}
		Rating r = ratingService.getRatingById(reviewId);
		int bookId = r.getBook().getId();
		if(userLogin.getId() == r.getUser().getId()) {			
			ratingService.deleteRating(reviewId);
			double averageStars = ratingService.calculateAverageStars(bookId);
		    Book book = bookService.getBookById(bookId);
		    book.setAverageStars(averageStars);
		    bookService.saveBook(book);
		}else {
			redirectAttributes.addFlashAttribute("error", "Bạn không thể xóa đánh giá của người khác!");
		}
	    return "redirect:/book/" + bookId;
	}
	
	@PostMapping("/rating/addDelLike/{id}")
    public String likeRating(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin == null) {
            return "redirect:/user/login-page";
        }

        Rating rating = ratingService.getRatingById(id);
        int bookId = rating.getBook().getId();
      
        LikeRating existingLike = likeService.getLikeRatingByUserAndRating(userLogin, rating);
        if (existingLike != null) {
            likeService.deleteLike(existingLike.getId());
            return "redirect:/book/" + bookId;
        }

        LikeRating newLike = new LikeRating();
        newLike.setBook(rating.getBook());
        newLike.setRating(rating);
        newLike.setUser(userLogin);
        likeService.saveLike(newLike);

        return "redirect:/book/" + bookId;
    }
	
	
}
