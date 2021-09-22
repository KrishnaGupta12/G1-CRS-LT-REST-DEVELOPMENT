package com.lt.exception;

public class RoleNotFoundException extends Throwable {
    public String getMessage(){
        return  "Role Not Found";
    }
}
