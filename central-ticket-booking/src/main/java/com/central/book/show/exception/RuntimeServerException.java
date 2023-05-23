package com.central.book.show.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class RuntimeServerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private String field;
	
	public RuntimeServerException(String message) {
		super(message);
	}

	public RuntimeServerException(String field, String message) {
		super(message);
		this.field = field;
	}
	
}
