package com.lt.dao;

import com.lt.bean.Courses;
import com.lt.bean.Grade;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.GradeNotAddedException;
import com.lt.exception.ProfessorNotFoundException;
import com.lt.exception.StudentNotFoundException;

import java.sql.SQLException;
import java.util.List;

/**
 *  Professor DAO Interface with all professor Methods
 */
public interface ProfessorDaoInterface {
	
    
    /**
     * Method to show list of courses teach by professor student from database
     *
     * @param professorId: professor id for which list of courses should be displayed
     * @return some set  of pending payments
     */
    public List<Courses> getCourseList(long professorId) throws SQLException,CourseNotAssignedToProfessorException;

    /**
     * Method to show list of students from database
     *
     * @param studentId:  studentId for which display list of students
     * @param semesterId: semesterId list for this student
     * @return some list  of students
     */
    public List<Student> getStudentList(long professor_id) throws SQLException;

    /**
     * Method to add grade to database
     *
     * @param grade: Grade to be added for course
     * @return void
     */
    public boolean addGrade(Grade grade) throws SQLException,GradeNotAddedException;

    /**
     * Method to show list of courses registered for student
     *
     * @param studentId: student id for which the student to be displayed
     * @param semesterId: semester id for which the student to be displayed
     * @return some list  of courses
     */
    public List<Courses> getListofRegCourses( long studentId,long semesterId) throws SQLException, StudentNotFoundException;

    /**
     * Method to show get professor  from database
     *
     * @param username: username for which professor details will be fetch from db
     * @return some set  of pending payments
     */
    public Professor getProfessorId(String username) throws SQLException, ProfessorNotFoundException;


}