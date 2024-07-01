package com.bookStoreFullStack.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreFullStack.entity.Author;
import com.bookStoreFullStack.repository.AuthorRepository;
import com.bookStoreFullStack.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public Author saveAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public Author getAuthorById(int id) {
		return authorRepository.findById(id).get();
	}

	@Override
	public Author updateAuthor(Author Author) {
		return authorRepository.save(Author);
	}

	@Override
	public void deleteAuthor(int id) {
		 authorRepository.deleteById(id);
	}
}
