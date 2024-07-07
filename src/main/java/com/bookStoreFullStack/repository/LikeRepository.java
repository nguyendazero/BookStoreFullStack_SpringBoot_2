package com.bookStoreFullStack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Integer>{
	List<Like> findByBookId(int bookId);
}
