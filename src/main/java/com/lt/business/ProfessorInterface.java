package com.lt.business;

import com.lt.bean.Courses;
import com.lt.bean.Grade;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.GradeNotAddedException;
import com.lt.exception.ProfessorNotFoundException;
import com.lt.exception.StudentNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProfessorInterface {
   

    public  List<Courses>  viewFullCourses(long professorId) throws  CourseNotAssignedToProfessorException,SQLException ;

    public List<Student> viewRegisteredStudents(long professorId) throws SQLException;
    
    public boolean addGrade(Grade grade) throws SQLException,GradeNotAddedException;

    public List<Courses> getListofRegCourses( long studentId,long semesterId) throws SQLException;

    public Professor getProfessorId(String username) throws SQLException, ProfessorNotFoundException;
}
