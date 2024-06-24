package com.bookStoreFullStack.serviceImpl;

import org.springframework.stereotype.Service;

import com.bookStoreFullStack.repository.LikeRepository;
import com.bookStoreFullStack.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService{
	private LikeRepository likeRepository;
}
