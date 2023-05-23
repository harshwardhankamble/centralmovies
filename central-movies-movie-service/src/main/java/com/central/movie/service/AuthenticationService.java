package com.central.movie.service;

import java.util.Date;

import com.central.book.common.dto.AccessTokenResponse;

public interface AuthenticationService {

	public AccessTokenResponse generateAuthToken(Integer userId, String username, Date dateOfBirth);
}
