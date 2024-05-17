package com.bookStoreFullStack.serviceImpl;

import org.springframework.stereotype.Service;

import com.bookStoreFullStack.repository.CategoryRepository;
import com.bookStoreFullStack.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	private CategoryRepository categoryRepository;
}
