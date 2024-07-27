package com.bookStoreFullStack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	    if (!model.containsAttribute("discountedTotal")) {
	        model.addAttribute("discountedTotal", null);
	    }

	    model.addAttribute("cart", cart);
	    return "cart";
	}
	
	@PostMapping("/cart/apply-coupon")
	public String applyCoupon(@RequestParam("couponCode") String couponCode, RedirectAttributes redirectAttributes) {
	    User userLogin = (User) session.getAttribute("userLogin");

	    Cart cart = cartService.viewCart(userLogin); 
	    double discountedTotal = cart.getTotal(); 
	    String errorMessage = null;

	    double result = cartService.applyCoupon(userLogin, couponCode);
	    if (result != cart.getTotal()) {
	        discountedTotal = result; 
	    } else {
	        errorMessage = "Invalid or expired coupon code."; 
	    }

	    redirectAttributes.addFlashAttribute("discountedTotal", discountedTotal);
	    redirectAttributes.addFlashAttribute("cart", cart);
	    redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

	    return "redirect:/cart";
	}
	
	@GetMapping("/cart/checkout")
	public String checkout(Model model) {
		return "checkout";
	}
}
