package com.bookStoreFullStack.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.repository.CartRepository;
import com.bookStoreFullStack.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByIdUser(int id_user) {
        return cartRepository.findByUserId(id_user);
    }

    @Override
    public void updateCart(Cart cart) {
    	cart.calculateTotal();
        cartRepository.save(cart);
    }

    @Override
    public void deleteCart(int id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Cart with id " + id + " does not exist");
        }
    }

	@Override
	public Cart viewCart(User user) {
        Cart cart = getCartByIdUser(user.getId());       
        updateCart(cart);       
        return cart;
    }
}
