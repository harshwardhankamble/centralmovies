package com.central.movie.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.central.book.common.constant.Constants;
import com.central.book.common.entity.Authentication;
import com.central.book.common.entity.User;
import com.central.book.common.exception.ContentNotFoundException;
import com.central.book.common.exception.RuntimeServerException;
import com.central.book.common.message.Message;
import com.central.movie.repository.AuthenticationRepository;
import com.central.movie.repository.UserRepository;

@Component
public class AccessHandler {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationRepository authenticationRepository;
	
	public boolean checkUserAccess(Integer userId, String[] roles) {
		Authentication authentication = authenticationRepository.findFirstByUserIdOrderByUserIdDesc(userId);
		if (authentication == null || authentication.getTokenTimeStamp() >= System.currentTimeMillis() + Constants.JWT_TOKEN_VALIDITY * 1000) {
			
			throw new RuntimeServerException("Token expired: Please regenerate token");
		}
		
		User user = userRepository.findById(userId).orElse(null);
		
		if (user == null) {
			throw new ContentNotFoundException(Message.formatMessage(Message.USER_NOT_FOUND, "User ID", String.valueOf(userId)));
		}
		
		for(String role: roles) {
			if (user.getRole().getRoleName().equals(role)) {
				return true;
			}
		}
		
		return false;
	}
}
