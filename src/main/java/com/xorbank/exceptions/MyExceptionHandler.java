package com.xorbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> userNotFoundException(UserNotFoundException ex) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExcpetionHandler(Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
