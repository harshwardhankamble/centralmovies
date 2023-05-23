package com.central.movie.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.book.common.dto.AccessTokenResponse;
import com.central.book.common.exception.GlobalExceptionResponseEntity;
import com.central.movie.service.AuthenticationService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication")
@Import(GlobalExceptionResponseEntity.class)
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@GetMapping("/generate-auth-token")
	public AccessTokenResponse generateAuthAccessTokenResponse(@RequestParam Integer userId,
			@RequestParam String username, @RequestParam(required = false) Date dateOfBirth) {

		return authenticationService.generateAuthToken(userId, username, dateOfBirth);
	}
}
