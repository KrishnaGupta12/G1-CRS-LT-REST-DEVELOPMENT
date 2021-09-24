package com.lt.exception;

public class StudentAlreadyRegisteredException extends Exception {
 
	 public StudentAlreadyRegisteredException() {}
	
	 public String getMessage(long studentID) {
		 
		 return "Student ID Already Registered ::"+studentID;
		 
	 }
	 
 public String getMessage() {
		 
		 return "Student Already Registered";
		 
	 }

}
