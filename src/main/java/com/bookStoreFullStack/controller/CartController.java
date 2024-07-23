package com.bookStoreFullStack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.service.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private HttpSession session;
	
	@GetMapping("/cart")
	public String viewCart(HttpSession session, Model model) {
        User userLogin = (User) session.getAttribute("userLogin");
        if (userLogin == null) {
            return "redirect:/user/login-page";
        }

        Cart cart = cartService.viewCart(userLogin);
        model.addAttribute("cart", cart);
        return "cart";
    }
}
