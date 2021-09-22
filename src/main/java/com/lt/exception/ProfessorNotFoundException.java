package com.lt.exception;

public class ProfessorNotFoundException extends Exception {
	private String userName;

	public ProfessorNotFoundException(String userName) {
		
		this.userName = userName;
	}
	
	public String getMessage(String userName)
	{
		return "Professor" +userName+ "not found!!" ;
	}

}