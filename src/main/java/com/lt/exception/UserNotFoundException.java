package com.lt.exception;

public class UserNotFoundException extends Throwable {
    private String username;
    private String password;

    public UserNotFoundException(String username, String password)
    {
        this.username=username;
        this.password=password;
    }

    public String getMessage(String username,String password){
        return "Invalid username and password for userId "+username;
    }

}
