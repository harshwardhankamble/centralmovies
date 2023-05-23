package com.central.book.show.service;

import com.central.book.show.entity.User;

public interface UserService {

	public boolean checkUserWithUsername(String username);
	
	public boolean checkUserWithEmail(String emailId);
	
	public void registerNewUser(User user);
}
