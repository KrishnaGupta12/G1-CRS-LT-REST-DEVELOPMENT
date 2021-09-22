package com.lt.exception;

public class CourseDetailsNotFoundException extends Exception {
    public CourseDetailsNotFoundException(){
    }
    public String getMsg()
    {
        return " Sorry Course Not Found !!!   :";
    }
}
