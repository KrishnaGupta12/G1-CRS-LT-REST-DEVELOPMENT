package com.lt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Sorry Course Not Found !!!")
public class CourseDetailsNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseDetailsNotFoundException(){
    }
	
//	@ExceptionHandler(value = CourseDetailsNotFoundException.class)
//    public ResponseEntity handleBlogAlreadyExistsException(CourseDetailsNotFoundException blogAlreadyExistsException) {
//        return new ResponseEntity("Sorry Course Not Found !!!",HttpStatus.NOT_FOUND);
//    }
    
    //@ExceptionHandler(CourseDetailsNotFoundException.class)
    public String getMsg()
    {
        return " Sorry Course Not Found !!!   :";
        
        //return new ResponseEntity<>("Sorry Course Not Found !!!....",HttpStatus.NOT_FOUND);
    }
}
