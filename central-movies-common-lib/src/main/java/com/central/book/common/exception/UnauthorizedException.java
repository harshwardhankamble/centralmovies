package com.central.book.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private String field;
	
	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(String field, String message) {
		super(message);
		this.field = field;
	}
	
}
