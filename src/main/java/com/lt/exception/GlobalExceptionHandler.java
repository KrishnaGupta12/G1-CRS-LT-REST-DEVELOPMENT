package com.lt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(value=CourseNotAssignedToProfessorException.class)
	public ResponseEntity handleException(CourseNotAssignedToProfessorException e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value=StudentNotFoundException.class)
	public ResponseEntity handleException(StudentNotFoundException e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=GradeNotAddedException.class)
	public ResponseEntity handleException(GradeNotAddedException e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	}

	
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
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = StudentAlreadyRegisteredException.class)
	public ResponseEntity handleException(StudentAlreadyRegisteredException e) {
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = CourseNotFoundException.class)
	public ResponseEntity handleException(CourseNotFoundException e) {
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(value = CourseExistedException.class)
	public ResponseEntity handleException(CourseExistedException e) {
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = StudentDetailsNotFoundException.class)
	public ResponseEntity handleException(StudentDetailsNotFoundException e) {
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
}
