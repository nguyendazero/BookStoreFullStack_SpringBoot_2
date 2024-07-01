package com.bookStoreFullStack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
