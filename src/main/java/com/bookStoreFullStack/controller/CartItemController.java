package com.bookStoreFullStack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookStoreFullStack.service.CartItemService;

@Controller
public class CartItemController {
	@Autowired
	private CartItemService cartItemService;
	
	@GetMapping("/cart-item/plus/{id}")	
    public String plusItem(@PathVariable("id") int cartItemId, Model model) {
        cartItemService.increaseQuantity(cartItemId);
        return "redirect:/cart";
    }

    @GetMapping("/cart-item/minus/{id}")
    public String minusItem(@PathVariable("id") int cartItemId, Model model) {
        cartItemService.decreaseQuantity(cartItemId);
        return "redirect:/cart";
    }
}
