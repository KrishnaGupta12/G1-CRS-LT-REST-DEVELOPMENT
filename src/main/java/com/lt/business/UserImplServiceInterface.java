package com.lt.business;

import com.lt.bean.Roles;
import com.lt.dao.UserDaoInterface;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.GradeNotAddedException;
import com.lt.exception.ProfessorNotFoundException;
import com.lt.exception.StudentNotFoundException;
import com.lt.exception.UserNotFoundException;
import com.lt.dao.UserDaoImpl;
import com.lt.exception.RoleNotFoundException;
import com.lt.exception.StudentDetailsNotFoundException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author User Implementation with DAO Layer
 */
public class UserImplServiceInterface {

    private static Logger logger = Logger.getLogger(UserImplServiceInterface.class);

    //    @Override
//    public void getUserName() {
//
//    }
//
//    @Override
//    public void gatPassword() {
//
//    }
    UserDaoImpl userDao = UserDaoImpl.getInstance();


    public int login(String username, String password) throws SQLException, UserNotFoundException {
        return userDao.login(username, password);
    }



    public void getUserMenu(int role, String userName) throws SQLException, IOException {
       userDao.getUserMenu(role,userName);
    }
}
