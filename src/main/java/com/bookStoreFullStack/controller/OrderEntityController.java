package com.bookStoreFullStack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bookStoreFullStack.service.OrderEntityService;

@Controller
public class OrderEntityController {
	@Autowired
	private OrderEntityService orderEntityService;
}
