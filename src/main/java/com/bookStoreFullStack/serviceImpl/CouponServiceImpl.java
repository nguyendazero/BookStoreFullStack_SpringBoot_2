package com.bookStoreFullStack.serviceImpl;

import org.springframework.stereotype.Service;

import com.bookStoreFullStack.repository.CouponRepository;
import com.bookStoreFullStack.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService{
	private CouponRepository couponRepository;
}
