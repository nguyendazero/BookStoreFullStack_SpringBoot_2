package com.bookStoreFullStack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
