package com.example.cms.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.exception.UserAlreadyExistByEmailException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler {
	
	private ErrorStructure<String> errStructure;
	
	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status,String message,String rootCause){
		return new ResponseEntity<ErrorStructure<String>>(errStructure.setStatusCode(status.value())
				                                                      .setMessage(message)
				                                                      .setRootCause(rootCause),status);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserAlreadyExistByEmail(UserAlreadyExistByEmailException e) {
		return errorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), "User Already registered with the entered email");
	}
}
