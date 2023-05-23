package com.central.movie.service;

import com.central.book.common.entity.User;

public interface UserService {

	public boolean checkUserWithUsername(String username);
	
	public boolean checkUserWithEmail(String emailId);
	
	public void registerNewUser(User user);
	
	public User getUserDetailsById(Integer userId);
}
