package com.lt.business;

import com.lt.bean.Courses;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.dao.AdminDaoImpl;
import com.lt.dao.AdminDaoInterface;
import com.lt.exception.CourseExistedException;
import com.lt.exception.StudentDetailsNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@Component
public class AdminImplService  implements AdminDaoInterface {
    private static Logger logger = Logger.getLogger(AdminImplService.class);
    
    @Autowired
    AdminDaoImpl adminDao ;
    boolean flag = false;


    @Override
    public boolean addProfessor(Professor professor) throws SQLException {
        flag = adminDao.addProfessor(professor);
        return flag;

    }

    @Override
    public boolean approveStudent(int studentId) throws SQLException, StudentDetailsNotFoundException {
    flag = adminDao.approveStudent(studentId);
    return flag;
    }

    @Override
    public List<Student> showListOfPendingStudent() throws SQLException {

        List<Student> pendingStudent = adminDao.showListOfPendingStudent();
        return pendingStudent;
    }

    @Override
    public boolean generateReportCard() throws SQLException{
        flag = adminDao.generateReportCard();
        return flag;

    }

    @Override
    public boolean addCourse(Courses course) throws SQLException, CourseExistedException {
       
          flag = adminDao.addCourse(course);
          return flag;
       
       

    }

    @Override
    public boolean deleteCourse(long courseId,List<Courses> coursesList) throws IOException, SQLException {
    	flag =adminDao.deleteCourse(courseId,coursesList);
    	return flag;

    }


    @Override
    public List<Courses> adminViewAllCourses() throws SQLException {

        List<Courses> viewAllCourses = adminDao.adminViewAllCourses();

        return viewAllCourses;
    }






}
