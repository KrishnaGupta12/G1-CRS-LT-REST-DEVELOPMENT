package com.lt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(value=CourseNotAssignedToProfessorException.class)
	public ResponseEntity handleException(CourseNotAssignedToProfessorException e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		//return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage(professorId));
	}
	
	@ExceptionHandler(value=StudentNotFoundException.class)
	public ResponseEntity handleException(StudentNotFoundException e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=GradeNotAddedException.class)
	public ResponseEntity handleException(GradeNotAddedException e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
}
