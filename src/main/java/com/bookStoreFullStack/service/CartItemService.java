package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.CartItem;
import com.bookStoreFullStack.entity.User;


public interface CartItemService {
	
	void addToCart(int bookId, User user);
	
	void removeFromCart(int cartItemId, User user);
	
	List<CartItem> getAllCartItemByCartId(int id_cart);
	
	CartItem saveCartItem(CartItem cart_item);
	
	void deleteCartItem(int id);

	CartItem getCartItemById(int itemId);

	void removeAllCartItemsByIdCart(int id_cart);
	
	void increaseQuantity(int cartItemId);
	
    void decreaseQuantity(int cartItemId);
}
