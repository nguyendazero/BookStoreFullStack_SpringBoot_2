package com.bookStoreFullStack.serviceImpl;

import org.springframework.stereotype.Service;

import com.bookStoreFullStack.repository.AuthorRepository;
import com.bookStoreFullStack.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{
	private AuthorRepository authorRepository;
}
