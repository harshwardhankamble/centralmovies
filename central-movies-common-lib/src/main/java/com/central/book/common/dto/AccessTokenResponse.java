package com.central.book.common.dto;

import lombok.Data;

@Data
public class AccessTokenResponse {

	private String userSessionToken;
	
	private String accessDeniedReason;
}
