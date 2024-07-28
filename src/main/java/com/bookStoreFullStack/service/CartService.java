package com.bookStoreFullStack.service;



import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.User;


public interface CartService {
	Cart saveCart(Cart user);
	
	Cart getCartByIdUser(int id_user);
	
	void updateCart(Cart Cart);
	
	void deleteCart(int id);
	
	Cart viewCart(User user);
	
	Double applyCoupon(User user, String couponCode);

	public void clearCartItems(User user);
}
