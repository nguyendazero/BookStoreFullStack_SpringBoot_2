package com.bookStoreFullStack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.service.CartService;
import com.bookStoreFullStack.service.CouponService;

import jakarta.servlet.http.HttpSession;

public class CouponController {
	@Autowired
	private CouponService couponService;
	@Autowired
	private CartService cartService;
	@Autowired
	private HttpSession session;
	
}
