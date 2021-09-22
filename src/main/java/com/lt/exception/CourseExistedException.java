package com.lt.exception;

public class CourseExistedException extends Exception{
    public CourseExistedException(){
    }
    public String getMsg(long courseId){
        return "Course id  Already Existed  : "+courseId;

    }

}
