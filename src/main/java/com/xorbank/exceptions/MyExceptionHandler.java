package com.xorbank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.xorbank.response.MessageResponse;

@RestControllerAdvice
public class MyExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public MessageResponse userNotFoundException(UserNotFoundException ex) {
		//ResponseMessage errorDetails = new ResponseMessage(ex.getMessage(),400);
		return new MessageResponse(ex.getMessage(), 400);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExcpetionHandler(Exception ex) {
		MessageResponse errorDetails = new MessageResponse(ex.getMessage(),400);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
