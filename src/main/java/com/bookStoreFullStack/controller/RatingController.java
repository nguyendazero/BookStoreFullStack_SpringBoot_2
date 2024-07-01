package com.bookStoreFullStack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bookStoreFullStack.service.RatingService;

@Controller
public class RatingController {
	@Autowired
	private RatingService ratingService;
}
