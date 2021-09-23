package com.lt.exception;

public class GradeNotAddedException extends Exception {

public long studentId;
	
	public GradeNotAddedException(long studentId)
	{	
		this.studentId = studentId;
	}
	
	public GradeNotAddedException() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage(long studentId){
		return "Grade Assigning Failed for Student with Id:"+studentId;
	}
	
	public String getMessage() {
		return "Grade can't be added..!";
	}
}