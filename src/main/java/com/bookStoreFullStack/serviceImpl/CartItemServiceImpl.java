package com.bookStoreFullStack.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreFullStack.entity.CartItem;
import com.bookStoreFullStack.repository.CartItemRepository;
import com.bookStoreFullStack.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getAllCartItemByCartId(int id_cart) {
        return cartItemRepository.findByCartId(id_cart);
    }

    @Override
    public CartItem saveCartItem(CartItem cart_item) {
        return cartItemRepository.save(cart_item);
    }

    @Override
    public void deleteCartItem(int id) {
        if (cartItemRepository.existsById(id)) {
            cartItemRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("CartItem with id " + id + " does not exist");
        }
    }

    @Override
    public CartItem getCartItemById(int itemId) {
        return cartItemRepository.findById(itemId).get(itemId);
    }

    @Override
    public void removeAllCartItemsByIdCart(int id_cart) {
        List<CartItem> cartItems = cartItemRepository.findByCartId(id_cart);
        cartItemRepository.deleteAll(cartItems);
    }
}
