package com.bookStoreFullStack.serviceImpl;

import org.springframework.stereotype.Service;

import com.bookStoreFullStack.repository.UserRepository;
import com.bookStoreFullStack.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	private UserRepository userRepository;

}
