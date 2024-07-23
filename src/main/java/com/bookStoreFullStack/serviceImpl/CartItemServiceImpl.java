package com.bookStoreFullStack.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.CartItem;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.repository.CartItemRepository;
import com.bookStoreFullStack.service.BookService;
import com.bookStoreFullStack.service.CartItemService;
import com.bookStoreFullStack.service.CartService;

import jakarta.servlet.http.HttpSession;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
	private HttpSession session;
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;

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
        return cartItemRepository.findById(itemId);
    }

    @Override
    public void removeAllCartItemsByIdCart(int id_cart) {
        List<CartItem> cartItems = cartItemRepository.findByCartId(id_cart);
        cartItemRepository.deleteAll(cartItems);
    }

	@Override
	public void increaseQuantity(int cartItemId) {
	    User userLogin = (User) session.getAttribute("userLogin");
	    Cart cart = cartService.getCartByIdUser(userLogin.getId());
	    if (cart != null) {
	        List<CartItem> cartItems = getAllCartItemByCartId(cart.getId()); // Assuming getAllCartItemByCartId() returns a list of CartItem
	        for (CartItem item : cartItems) {
	            if (item.getId() == cartItemId) {
	                item.setQuantity(item.getQuantity() + 1);
	                cartItemRepository.save(item);
	                cartService.updateCart(cart);
	                break;
	            }
	        }
	    } 
	}

	@Override
	public void decreaseQuantity(int cartItemId) {
		User userLogin = (User) session.getAttribute("userLogin");
	    Cart cart = cartService.getCartByIdUser(userLogin.getId());
	    if (cart != null) {
	        List<CartItem> cartItems = getAllCartItemByCartId(cart.getId()); 
	        for (CartItem item : cartItems) {
	            if (item.getId() == cartItemId) {
	            	if(item.getQuantity() >= 2) {
	            		item.setQuantity(item.getQuantity() - 1);
		                cartItemRepository.save(item); 
		                cartService.updateCart(cart);
		                break;
	            	} 
	            }
	        }
	    } 
		
	}

	@Override
	public void addToCart(int bookId, User user) {
	    Cart cart = cartService.getCartByIdUser(user.getId());

	    Book bookAddCart = bookService.getBookById(bookId);
	    boolean itemExists = false;

	    for (CartItem item : cart.getItems()) {
	        if (item.getBook().getId() == bookId) {
	            item.setQuantity(item.getQuantity() + 1);
	            cartItemRepository.save(item);
	            itemExists = true;
	            break;
	        }
	    }

	    if (!itemExists) {
	        CartItem newItem = new CartItem();
	        newItem.setBook(bookAddCart);
	        newItem.setQuantity(1);
	        newItem.setCart(cart);
	        
	        cart.getItems().add(newItem);
	        cartItemRepository.save(newItem);
	    }

	    cartService.updateCart(cart);
	}

	@Override
	public void removeFromCart(int cartItemId, User user) {

        Cart cart = cartService.getCartByIdUser(user.getId());
        
        CartItem itemToRemove = cartItemRepository.findById(cartItemId);

        if (itemToRemove != null && cart.getItems().contains(itemToRemove)) {
            cart.getItems().remove(itemToRemove);
            
            cartItemRepository.delete(itemToRemove);
            cartService.updateCart(cart);
        }
    }
}
