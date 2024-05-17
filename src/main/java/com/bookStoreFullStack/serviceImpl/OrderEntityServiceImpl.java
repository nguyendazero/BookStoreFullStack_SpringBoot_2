package com.bookStoreFullStack.serviceImpl;

import org.springframework.stereotype.Service;

import com.bookStoreFullStack.repository.OrderEntityRepository;
import com.bookStoreFullStack.service.OrderEntityService;

@Service
public class OrderEntityServiceImpl implements OrderEntityService{
	private OrderEntityRepository orderEntityRepository;
}
