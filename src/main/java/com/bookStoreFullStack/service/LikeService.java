package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.LikeRating;
import com.bookStoreFullStack.entity.Rating;
import com.bookStoreFullStack.entity.User;

public interface LikeService {
	List<LikeRating> getLikesByBook(int bookId);
	
	LikeRating saveLike(LikeRating Like);
	
	void deleteLike(int id);

	List<LikeRating> findLikesByRatingId(int ratingId);
	
	LikeRating findLikeByUserAndRating(User user, Rating rating);
}
