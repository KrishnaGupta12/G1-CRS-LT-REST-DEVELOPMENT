package com.lt.exception;

public class GradeNotAddedException extends Exception {

public long studentId;
	
	public GradeNotAddedException(long studentId)
	{	
		this.studentId = studentId;
	}
	
	public String getMessage(long studentId){
		return "Grade Assigning Failed for Student with Id:"+studentId;
	}
}