package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.Comment;


public interface CommentService {
	List<Comment> getCommentsByBook(int bookId);
	
	Comment saveComment(Comment Comment);
	
	Comment getCommentById(int id);
	
	Comment updateComment(Comment Comment);
	
	void deleteComment(int id);

	
}
