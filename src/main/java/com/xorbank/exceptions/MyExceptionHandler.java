package com.xorbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseMessage userNotFoundException(UserNotFoundException ex) {
		//ResponseMessage errorDetails = new ResponseMessage(ex.getMessage(),400);
		return new ResponseMessage(ex.getMessage(), 400);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExcpetionHandler(Exception ex) {
		ResponseMessage errorDetails = new ResponseMessage(ex.getMessage(),400);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
