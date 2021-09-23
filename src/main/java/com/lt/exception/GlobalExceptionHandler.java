package com.lt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
//@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = CourseDetailsNotFoundException.class)
	public ResponseEntity handleException(CourseDetailsNotFoundException e) {
		
		return new ResponseEntity<>(e.getMsg(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CourseAlreadyRegisteredException.class)
	public ResponseEntity handleException(CourseAlreadyRegisteredException e) {
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity handleException(UserNotFoundException e) {
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = StudentAlreadyRegisteredException.class)
	public ResponseEntity handleException(StudentAlreadyRegisteredException e) {
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	}
	
	
	

}
