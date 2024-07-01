package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.Rating;

public interface RatingService {
	List<Rating> getRatingsByBook(int bookId);
	
	Rating saveRating(Rating Rating);
	
	Rating getRatingById(int id);
	
	Rating updateRating(Rating Rating);
	
	void deleteRating(int id);
}
