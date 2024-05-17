package com.bookStoreFullStack.serviceImpl;

import org.springframework.stereotype.Service;

import com.bookStoreFullStack.repository.RatingRepository;
import com.bookStoreFullStack.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
	private RatingRepository ratingRepository;
}
