package com.central.movie.service;

import java.util.List;

import com.central.book.common.dto.UserDto;
import com.central.book.common.entity.User;

public interface UserService {

	public boolean checkUserWithUsername(String username);
	
	public boolean checkUserWithEmail(String emailId);
	
	public void registerNewUser(User user);
	
	public User getUserDetailsById(Integer userId);

	public void changeRoleOfUser(Integer userId, Integer roleId);

	public User validateCredentialsAndGenerateAccessToken(UserDto userDto);

	public List<User> getAllCustomers();
}
