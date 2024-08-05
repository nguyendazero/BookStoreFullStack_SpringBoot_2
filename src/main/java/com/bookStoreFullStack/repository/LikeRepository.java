package com.bookStoreFullStack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.LikeRating;
import com.bookStoreFullStack.entity.Rating;
import com.bookStoreFullStack.entity.User;

public interface LikeRepository extends JpaRepository<LikeRating, Integer>{
	List<LikeRating> findByBookId(int bookId);
	List<LikeRating> findByRatingId(int ratingId);
	LikeRating findByUserAndRating(User user, Rating rating);
}
