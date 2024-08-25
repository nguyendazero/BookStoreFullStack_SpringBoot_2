package com.bookStoreFullStack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.entity.Coupon;
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
	
	@GetMapping("/admin/coupon")
	public String Coupon(Model model) {
		List<Coupon> coupons = couponService.getAllCoupons();
		model.addAttribute("coupons", coupons);
		
		return "admin/coupon";
	}
	
}
