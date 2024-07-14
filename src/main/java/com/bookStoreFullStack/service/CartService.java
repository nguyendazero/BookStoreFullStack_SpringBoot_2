package com.bookStoreFullStack.service;

import com.bookStoreFullStack.entity.Cart;


public interface CartService {
	Cart saveCart(Cart user);
	
	Cart getCartByUser(int id_user);
	
	Cart updateCart(Cart Cart);
	
	void deleteCart(int id);
}
