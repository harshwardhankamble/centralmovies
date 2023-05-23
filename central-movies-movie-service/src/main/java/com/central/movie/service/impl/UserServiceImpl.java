package com.central.movie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.common.entity.Role;
import com.central.book.common.entity.User;
import com.central.book.common.exception.ContentNotFoundException;
import com.central.book.common.exception.UserAlreadyExist;
import com.central.book.common.message.Message;
import com.central.movie.repository.RoleRepository;
import com.central.movie.repository.UserRepository;
import com.central.movie.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public boolean checkUserWithUsername(String username) {
		
		return userRepository.existsByUserName(username);
	}

	@Override
	public boolean checkUserWithEmail(String emailId) {
		
		return userRepository.existsByEmailId(emailId);
	}

	@Override
	public void registerNewUser(User user) {
		
		if (checkUserWithEmail(user.getEmailId())) {
			throw new UserAlreadyExist(Message.formatMessage(Message.USER_ALREADY_EXIST, "EmailID", user.getEmailId(), "EmailID"));
		}
		
		if (checkUserWithUsername(user.getUserName())) {
			throw new UserAlreadyExist(Message.formatMessage(Message.USER_ALREADY_EXIST, "Username", user.getUserName(), "Username"));
		}
		
		Role role = null;
		if (user.getRole() != null && user.getRole().getRoleName() != null) {
			role = roleRepository.findOneByRoleName(user.getRole().getRoleName());
		} else {
			role = roleRepository.findOneByRoleName("Customer"); 
		}
		 
		user.setRole(role);
		
		userRepository.save(user);
		
	}

	@Override
	public User getUserDetailsById(Integer userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			throw new ContentNotFoundException(Message.formatMessage(Message.USER_NOT_FOUND, "User Id", userId));
		}
		return user;
	}

}
