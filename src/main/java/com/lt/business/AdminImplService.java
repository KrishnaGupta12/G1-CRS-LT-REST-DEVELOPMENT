package com.lt.business;

import com.lt.bean.Courses;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.dao.AdminDaoImpl;
import com.lt.dao.AdminDaoInterface;
import com.lt.exception.CourseExistedException;
import com.lt.exception.StudentDetailsNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Component
public class AdminImplService  implements AdminDaoInterface {
    private static Logger logger = Logger.getLogger(AdminImplService.class);

    AdminDaoImpl adminDao = AdminDaoImpl.getInstance();
    boolean flag = false;


    @Override
    public void addProfessor(Professor professor) throws SQLException {
        adminDao.addProfessor(professor);

    }

    @Override
    public void approveStudent(int studentId) throws SQLException, StudentDetailsNotFoundException {
    adminDao.approveStudent(studentId);
    }

    @Override
    public List<Student> showListOfPendingStudent() throws SQLException {

        List<Student> pendingStudent = adminDao.showListOfPendingStudent();
        return pendingStudent;
    }

    @Override
    public void generateReportCard() throws SQLException{
        adminDao.generateReportCard();

    }

    @Override
    public boolean addCourse(Courses course) throws SQLException {
       try{
           adminDao.addCourse(course);
       }catch (CourseExistedException e)
       {
           logger.error(e.getMsg(course.getCourseId()));
       }

        return false;
    }

    @Override
    public boolean deleteCourse(long courseId, List<Courses> coursesList) throws IOException, SQLException {
        adminDao.deleteCourse(courseId,coursesList);


        return false;
    }


    @Override
    public List<Courses> adminViewAllCourses() throws SQLException {

        List<Courses> viewAllCourses = adminDao.adminViewAllCourses();

        return viewAllCourses;
    }


    public boolean checkId(long course_id, List<Courses> list) {

        for (Courses c :list) {
            if(c.getCourseId() == course_id)
                return true;
        }
        return false;
    }

    }






