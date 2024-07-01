package com.bookStoreFullStack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStoreFullStack.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer>{

}
