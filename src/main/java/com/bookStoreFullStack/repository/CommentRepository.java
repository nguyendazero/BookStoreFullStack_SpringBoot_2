package com.bookStoreFullStack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	List<Comment> findByBookId(int bookId);
}
