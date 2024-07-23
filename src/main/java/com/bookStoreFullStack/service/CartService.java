package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.CartItem;
import com.bookStoreFullStack.entity.User;


public interface CartService {
	Cart saveCart(Cart user);
	
	Cart getCartByIdUser(int id_user);
	
	void updateCart(Cart Cart);
	
	void deleteCart(int id);
	
	Cart viewCart(User user);


}
