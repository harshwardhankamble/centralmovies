package com.central.book.show.service;

import java.util.Date;

import com.central.book.show.dto.AccessTokenResponse;

public interface AuthenticationService {

	public AccessTokenResponse generateAuthToken(Integer userId, String username, Date dateOfBirth);
}
