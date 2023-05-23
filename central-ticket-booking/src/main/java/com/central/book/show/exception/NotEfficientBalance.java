package com.central.book.show.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotEfficientBalance extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private String field;
	
	public NotEfficientBalance(String message) {
		super(message);
	}

	public NotEfficientBalance(String field, String message) {
		super(message);
		this.field = field;
	}
	
}
