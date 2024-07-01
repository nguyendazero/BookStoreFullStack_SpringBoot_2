package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.Category;

public interface CategoryService {
	
	List<Category> getAllCategories();
	
	Category saveCategory(Category Category);
	
	Category getCategoryById(int id);
	
	Category updateCategory(Category Category);
	
	void deleteCategory(int id);
}
