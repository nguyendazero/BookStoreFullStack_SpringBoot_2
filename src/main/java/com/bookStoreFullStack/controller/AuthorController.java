package com.bookStoreFullStack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bookStoreFullStack.service.AuthorService;

@Controller
public class AuthorController {
	@Autowired
	private AuthorService authorService;
}
