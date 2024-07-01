package com.bookStoreFullStack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
