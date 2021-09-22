package com.lt.exception;

public class CourseNotAssignedToProfessorException extends Exception {
	
private long professorId;

	public CourseNotAssignedToProfessorException(long professorId) {
	this.professorId = professorId;
}

	public String getMessage(long professorId){
	return "Course not assigned to Professor "  +professorId;
	}

}