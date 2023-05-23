package com.central.book.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyExist extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private String field;
	
	public UserAlreadyExist(String message) {
		super(message);
	}

	public UserAlreadyExist(String field, String message) {
		super(message);
		this.field = field;
	}
	
}
