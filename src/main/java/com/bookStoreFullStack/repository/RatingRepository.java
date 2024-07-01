package com.bookStoreFullStack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>{
	List<Rating> findByBookId(int bookId);
}
