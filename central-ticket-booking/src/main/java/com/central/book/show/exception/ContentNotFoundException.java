package com.central.book.show.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ContentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private String field;
	
	public ContentNotFoundException(String message) {
		super(message);
	}

	public ContentNotFoundException(String field, String message) {
		super(message);
		this.field = field;
	}
	
}
