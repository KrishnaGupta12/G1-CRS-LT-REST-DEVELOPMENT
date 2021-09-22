package com.lt.exception;

public class CourseNotFoundException extends Throwable {
    public CourseNotFoundException(long courseId) {
    }

    public String getMessage(long courseId) {
        return "Course with courseCode: " + courseId + " not found.";
    }
}
