package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.LikeRating;
import com.bookStoreFullStack.entity.Rating;
import com.bookStoreFullStack.entity.User;

public interface LikeRatingService {
	List<LikeRating> getLikesByBook(int bookId);
    LikeRating saveLike(LikeRating like);
    void deleteLike(int id);
    List<LikeRating> findLikesByRatingId(int ratingId);
    LikeRating getLikeRatingByUserAndRating(User user, Rating rating);
 
}
