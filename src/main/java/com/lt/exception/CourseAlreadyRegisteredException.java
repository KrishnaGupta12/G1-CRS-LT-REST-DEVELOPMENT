package com.lt.exception;

public class CourseAlreadyRegisteredException extends Exception {
    private String courseCode;

    /**
     * Constructor

     */
    public CourseAlreadyRegisteredException() {

    }



    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "You have already registered for this course.";
    }
}