package com.bookStoreFullStack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	List<Book> findByCategoryId(int categoryId);
    List<Book> findByAuthorId(int authorId);
    List<Book> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword);
}
