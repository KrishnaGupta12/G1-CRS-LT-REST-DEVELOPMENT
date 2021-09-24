package com.lt.dao;

import com.lt.bean.Roles;

import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.GradeNotAddedException;
import com.lt.exception.ProfessorNotFoundException;
import com.lt.exception.StudentNotFoundException;
import com.lt.exception.UserNotFoundException;

import com.lt.exception.RoleNotFoundException;
import com.lt.exception.StudentDetailsNotFoundException;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public interface UserDaoInterface {
	
	 /**
     * Method to login user
     *
     * @param username: username of user
     * @param password: password of user
     * @return value with update status
     */
	public int login(String username, String password) throws SQLException, UserNotFoundException;

	 /**
     * Method get role details
     *
     * @param roleId: roleId from roles table
     * @return Roles details for table
     */
    public Roles getRoleDetails(int roleId) throws SQLException, RoleNotFoundException;

    
    /**
     * Method to get login Time
     *
     * @return String  message with login time details
     */
    public String getLoginTime();


}
