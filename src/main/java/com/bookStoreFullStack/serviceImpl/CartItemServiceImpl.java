package com.bookStoreFullStack.serviceImpl;

import org.springframework.stereotype.Service;

import com.bookStoreFullStack.repository.CartItemRepository;
import com.bookStoreFullStack.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService{
	private CartItemRepository cartItemRepository;
}
