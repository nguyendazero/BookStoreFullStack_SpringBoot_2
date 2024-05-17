package com.bookStoreFullStack.serviceImpl;

import org.springframework.stereotype.Service;

import com.bookStoreFullStack.repository.BookRepository;
import com.bookStoreFullStack.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	private BookRepository bookRepository;
}
