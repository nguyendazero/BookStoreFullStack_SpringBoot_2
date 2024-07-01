package com.bookStoreFullStack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.CartItem;
import com.bookStoreFullStack.entity.CartItemId;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId>{
	List<CartItem> findByCartId(int cartId);
    boolean existsById(int id);
    void deleteById(int id);
    List<CartItem> findById(int itemId);
}
