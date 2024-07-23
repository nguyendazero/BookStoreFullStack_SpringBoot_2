package com.bookStoreFullStack.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreFullStack.entity.Cart;
import com.bookStoreFullStack.entity.Coupon;
import com.bookStoreFullStack.repository.CouponRepository;
import com.bookStoreFullStack.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon saveCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon getCouponById(int id) {
        return couponRepository.findById(id).orElse(null);
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) {
        if (couponRepository.existsById(coupon.getId())) {
            return couponRepository.save(coupon);
        } else {
            throw new IllegalArgumentException("Coupon with id " + coupon.getId() + " does not exist");
        }
    }

    @Override
    public void deleteCoupon(int id) {
        if (couponRepository.existsById(id)) {
            couponRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Coupon with id " + id + " does not exist");
        }
    }


}
