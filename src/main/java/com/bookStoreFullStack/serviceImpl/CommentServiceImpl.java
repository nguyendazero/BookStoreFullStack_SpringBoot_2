package com.bookStoreFullStack.serviceImpl;

import org.springframework.stereotype.Service;

import com.bookStoreFullStack.repository.CommentRepository;
import com.bookStoreFullStack.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	private CommentRepository commentRepository;
}
