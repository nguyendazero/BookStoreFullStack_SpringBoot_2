package com.bookStoreFullStack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.User;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	Cart findByUserId(int userId);
}
