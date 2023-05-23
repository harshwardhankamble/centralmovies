package com.central.book.show.dto;

import lombok.Data;

@Data
public class AccessTokenResponse {

	private String userSessionToken;
	
	private String accessDeniedReason;
}
