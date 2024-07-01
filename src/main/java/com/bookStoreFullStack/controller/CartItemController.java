package com.bookStoreFullStack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bookStoreFullStack.service.CartItemService;

@Controller
public class CartItemController {
	@Autowired
	private CartItemService cartItemService;
}
