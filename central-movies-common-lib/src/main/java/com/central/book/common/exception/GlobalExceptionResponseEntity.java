package com.central.book.common.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.central.book.common.response.ExceptionResponse;

@ControllerAdvice
public class GlobalExceptionResponseEntity extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ContentNotFoundException.class)
	public ResponseEntity<Object> handleContentNotFoundException(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExist.class)
	public ResponseEntity<Object> handleUserAlreadyExistException(Exception exception, WebRequest request){
		
		return getException(exception, request);
	}
	
	@ExceptionHandler(SeatIsNotVaccant.class)
	public ResponseEntity<Object> handleSeatIsNotVaccantException(Exception exception, WebRequest request){
		
		return getException(exception, request);
	}
	
	@ExceptionHandler(NotEfficientBalance.class)
	public ResponseEntity<Object> handleNotEfficientBalanceException(Exception exception, WebRequest request){
		
		return getException(exception, request);
	}
	
	@ExceptionHandler(RuntimeServerException.class)
	public ResponseEntity<Object> handleRuntimeServerException(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<Object> handleUnauthorizedExceptionException(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	
	private ResponseEntity<Object> getException(Exception exception, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	

}
