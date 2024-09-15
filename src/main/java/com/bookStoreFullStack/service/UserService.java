package com.bookStoreFullStack.service;

import java.util.List;

import com.bookStoreFullStack.entity.User;

public interface UserService {
	List<User> getAllUsers();
	
	User saveUser(User User);
	
	User getUserById(int id);
	
	User updateUser(User User);
	
	void deleteUser(int id);
	
	User getUserByUsernameAndPass(String userName, String password);
	
	User getUserByUsername(String username);
	
	User getUserByEmail(String username);
}
