package com.central.book.show.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.show.entity.Role;
import com.central.book.show.entity.User;
import com.central.book.show.exception.UserAlreadyExist;
import com.central.book.show.messages.Message;
import com.central.book.show.repository.RoleRepository;
import com.central.book.show.repository.UserRepository;
import com.central.book.show.service.UserService;

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

}
