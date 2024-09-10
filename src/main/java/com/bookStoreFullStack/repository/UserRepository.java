package com.bookStoreFullStack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUserNameAndPassword(String userName, String password);
	User findByUserName(String username);
}
