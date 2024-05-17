package com.bookStoreFullStack.serviceImpl;

import org.springframework.stereotype.Service;

import com.bookStoreFullStack.repository.OrderDetailRepository;
import com.bookStoreFullStack.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	private OrderDetailRepository orderDetailRepository;
}
