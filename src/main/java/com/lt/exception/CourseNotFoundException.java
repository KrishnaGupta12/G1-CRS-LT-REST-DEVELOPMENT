package com.lt.exception;

public class CourseNotFoundException extends Throwable {
    public CourseNotFoundException() {
    }

    public String getMessage() {
        return "Course : not found!!!";
    }
}
