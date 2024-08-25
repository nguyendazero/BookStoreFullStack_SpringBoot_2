package com.bookStoreFullStack.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookStoreFullStack.service.LikeRatingService;
import com.bookStoreFullStack.service.RatingService;

import jakarta.servlet.http.HttpSession;

public class LikeRatingController {
	@Autowired
	private LikeRatingService likeService;
	@Autowired
	private RatingService ratingService;
	@Autowired
	private HttpSession session;
	
		
}
