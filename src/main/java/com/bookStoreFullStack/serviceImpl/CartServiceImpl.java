package com.bookStoreFullStack.serviceImpl;

import org.springframework.stereotype.Service;

import com.bookStoreFullStack.repository.CartRepository;
import com.bookStoreFullStack.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	private CartRepository cartRepository;
}
