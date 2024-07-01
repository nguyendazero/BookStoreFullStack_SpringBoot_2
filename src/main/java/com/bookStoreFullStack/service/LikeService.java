package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.Like;

public interface LikeService {
	List<Like> getLikesByBook(int bookId);
	
	Like saveLike(Like Like);
	
	void deleteLike(int id);
}
