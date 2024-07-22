package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.CartItem;


public interface CartService {
	Cart saveCart(Cart user);
	
	Cart getCartByIdUser(int id_user);
	
	Cart updateCart(Cart Cart);
	
	void deleteCart(int id);
	


}
