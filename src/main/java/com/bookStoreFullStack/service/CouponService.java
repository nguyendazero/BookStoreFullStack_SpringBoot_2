package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.Coupon;



public interface CouponService {
	List<Coupon> getAllCoupons();
	
	Coupon saveCoupon(Coupon Coupon);
	
	Coupon getCouponById(int id);
	
	Coupon updateCoupon(Coupon Coupon);
	
	void deleteCoupon(int id);
}
