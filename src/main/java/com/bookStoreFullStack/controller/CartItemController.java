package com.bookStoreFullStack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.CartItem;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.service.BookService;
import com.bookStoreFullStack.service.CartItemService;
import com.bookStoreFullStack.service.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartItemController {
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private BookService bookService;
	@Autowired
	private CartService cartService;
	@Autowired
	private HttpSession session;
	
	@GetMapping("/cart-item/plus/{id}")	
    public String plusItem(@PathVariable("id") int cartItemId, RedirectAttributes redirectAttributes) {
        String errorIfTrue = cartItemService.increaseQuantity(cartItemId);
        redirectAttributes.addFlashAttribute("errorIfTrue", errorIfTrue);
        return "redirect:/cart";
    }

    @GetMapping("/cart-item/minus/{id}")
    public String minusItem(@PathVariable("id") int cartItemId, Model model) {
        cartItemService.decreaseQuantity(cartItemId);
        return "redirect:/cart";
    }
    
    @PostMapping("/cart/addToCart/{id}")
    public String addToCart(@PathVariable("id") int id, HttpSession session) {
        User userLogin = (User) session.getAttribute("userLogin");
        if(userLogin == null) {
        	return "login";
        }
        cartItemService.addToCart(id, userLogin);
        return "redirect:/cart";
    }
    
    @GetMapping("/cart-item/removeToCart/{id}")
    public String removeFromCart(@PathVariable("id") int id, HttpSession session) {
        User userLogin = (User) session.getAttribute("userLogin");
        cartItemService.removeFromCart(id, userLogin);
        return "redirect:/cart";
    }
}
