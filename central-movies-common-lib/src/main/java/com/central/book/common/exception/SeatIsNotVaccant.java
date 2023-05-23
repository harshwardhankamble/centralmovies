package com.central.book.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SeatIsNotVaccant extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private String field;
	
	public SeatIsNotVaccant(String message) {
		super(message);
	}

	public SeatIsNotVaccant(String field, String message) {
		super(message);
		this.field = field;
	}
	
}
