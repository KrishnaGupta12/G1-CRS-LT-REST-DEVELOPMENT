package com.lt.business;

import com.lt.bean.Courses;
import com.lt.bean.Professor;
import com.lt.bean.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AdminInterface  {


    public void addProfessor(Professor professor) throws SQLException;

    public void approveStudent(int studentId) throws SQLException;

    public List<Student> showListOfPendingStudent() throws SQLException;

    public void generateReportCard() throws SQLException;

    public void addCourse(Courses course) throws SQLException;

    public void deleteCourse(long courseId) throws IOException, SQLException;

    public List<Courses> adminViewAllCourses() throws SQLException;


}
