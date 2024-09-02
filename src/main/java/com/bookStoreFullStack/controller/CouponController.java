package com.bookStoreFullStack.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookStoreFullStack.entity.Author;
import com.bookStoreFullStack.entity.Coupon;
import com.bookStoreFullStack.service.CouponService;

@Controller
public class CouponController {
	@Autowired
	private CouponService couponService;
	
	

	@GetMapping("/admin/coupon")
	public String ManagerUserTest(Model model) {
		List<Coupon> coupons = couponService.getAllCoupons();
		model.addAttribute("coupons", coupons);
		return "admin/coupon";
	}
	
	@PostMapping("/admin/coupon/save")
	public String addCoupon(@RequestParam("code") String code,
							@RequestParam("expiry") Date expiry,
							@RequestParam("discount") int discount) {
		Coupon newConpon = new Coupon();
		newConpon.setCode(code);
		newConpon.setDiscount(discount);
		newConpon.setExpiry(expiry);
		couponService.saveCoupon(newConpon);
		return "redirect:/admin/coupon";
	}
	
	@PostMapping("/admin/coupon/update")
	public String updateCoupon(@ModelAttribute("coupon") Coupon coupon) {
	    couponService.saveCoupon(coupon);
	    return "redirect:/admin/coupon";
	}
	
	@GetMapping("/admin/coupons/delete/{id}")
	public String deleteCoupon(@PathVariable("id") int id) {
		couponService.deleteCoupon(id);
		return "redirect:/admin/coupon";
	}
}
