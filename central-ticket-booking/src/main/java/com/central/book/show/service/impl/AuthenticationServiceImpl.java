package com.central.book.show.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.central.book.show.constant.Constants;
import com.central.book.show.dto.AccessTokenResponse;
import com.central.book.show.entity.Authentication;
import com.central.book.show.entity.User;
import com.central.book.show.exception.ContentNotFoundException;
import com.central.book.show.messages.Message;
import com.central.book.show.repository.AuthenticationRepository;
import com.central.book.show.repository.UserRepository;
import com.central.book.show.service.AuthenticationService;
import com.central.book.show.util.JwtTokenUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationRepository authenticationRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public AccessTokenResponse generateAuthToken(Integer userId, String username, Date dateOfBirth) {
		
		AccessTokenResponse accessTokenResponse = new AccessTokenResponse();
		try {
			User user = userRepository.findByUserIdAndUserName(userId, username);
			
			if (user == null) {
				throw new ContentNotFoundException(Message.formatMessage(Message.USER_NOT_FOUND_WITH_USERID_USERNAME, userId, username));
			}
			
			removeIfAnyActiveUserTokenOrExpiredToken(userId, username);
			String token = jwtTokenUtil.generateAuthToken(userId, username, dateOfBirth);
			accessTokenResponse.setUserSessionToken(token);
			saveAccessTokenResponse(userId, username, token);
		} catch (ContentNotFoundException e) {
			accessTokenResponse.setAccessDeniedReason(e.getMessage());
			throw e;
		} catch (Exception e) {
			accessTokenResponse.setAccessDeniedReason("Error while generating access token");
			throw e;
		}
	
		return accessTokenResponse;
	}

	private void removeIfAnyActiveUserTokenOrExpiredToken(Integer userId, String username) {
		List<Authentication> authentications = authenticationRepository.findByUserIdAndUserName(userId, username);
		
		for(Authentication authentication: authentications) {
			if (System.currentTimeMillis() >= authentication.getTokenTimeStamp() + Constants.JWT_TOKEN_VALIDITY * 1000) {
				authenticationRepository.deleteById(authentication.getAuthenticationId());
			}
		}
	}
	
	private void saveAccessTokenResponse(Integer userId, String username, String token) {
		Authentication authentication = new Authentication();
		authentication.setToken(token);
		authentication.setUserId(userId);
		authentication.setUserName(username);
		authentication.setTokenTimeStamp(System.currentTimeMillis());
		
		authenticationRepository.save(authentication);
	}

}
