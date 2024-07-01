package com.bookStoreFullStack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bookStoreFullStack.service.CommentService;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
}
