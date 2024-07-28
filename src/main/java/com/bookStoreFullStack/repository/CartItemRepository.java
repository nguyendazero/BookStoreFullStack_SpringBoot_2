package com.bookStoreFullStack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
	List<CartItem> findByCartId(int cartId);
    boolean existsById(int id);
    void deleteByBookId(int bookId);
    CartItem findById(int itemId);
    void deleteByCart(Cart cart);
}
