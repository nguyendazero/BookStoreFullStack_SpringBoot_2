package com.bookStoreFullStack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bookStoreFullStack.service.OrderDetailService;

@Controller
public class OrderDetailController {
	@Autowired
	private OrderDetailService orderDetailService;
}
