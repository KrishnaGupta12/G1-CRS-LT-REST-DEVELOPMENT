package com.lt.dao;

import com.lt.bean.Courses;
import com.lt.bean.*;

import java.sql.SQLException;
import java.util.*;

/**
 *  Student DAO Interface with all student Methods
 */
public interface StudentDaoInterface {

    /**
     * Method to add student to database
     *
     * @param student: student object containing all the fields
     * @return some boolean if student is added
     */
    public boolean signUp(Student student) throws SQLException; //insert query

    /**
     * Method to add registered courses for student
     *
     * @param student_id: student id who wants to register
     * @param semesterid: for which semserter
     * @param courseId:   for which course
     * @return some boolean  if student is added
     */
    public boolean registerForCourse(long student_id, long semesterid, long courseId) throws SQLException; // insert query + select data from course table

    /**
     * Method to list registered courses for student
     *
     * @param studentId:  student id who wants to register
     * @param semesterId: for which semserter
     * @return some set  if registered courses is added
     */
    public Set<RegisterCourse> viewRegisteredCourses(long studentId, long semesterId) throws SQLException; // select query from registerCourse table

    /**
     * Method to remove course from database
     *
     * @param courseId: student id who wants to register
     * @return some set  if registered courses is added
     */
    public boolean removeCourse(long courseId) throws SQLException; // delete query from registerCourse table

    /**
     * Method to get student from database
     *
     * @param username: username of student
     * @return boolean value if course is deleted
     */
    public Student getStudent(String username) throws SQLException;

    /**
     * Method to show list of available course to  student from database
     *
     * @param semesterId: semeseter for which list need to be displayed
     * @return List of available courses
     */
    public List<Courses> showAvailableCourses(long semesterId) throws SQLException;

    /**
     * Method to show list of available course to  student from database
     *
     * @param student_id: display list for this student
     * @return some set  of pending payments
     */
    public Set<RegisterCourse> showListofPendingPayment(long student_id) throws SQLException;

    /**
     * Method to make payment  for   student and add to  database
     *
     * @param courseId: display list for this student
     * @param payment: display list for this student
     * @param studentid: display list for this student
     * @return boolean if payment is done
     */
    public boolean payfees(long courseId, Payment payment, long studentid) throws SQLException;

    /**
     * Method to show list of available course to  student from database
     *
     * @param semesterId: display list for this student
     * @param studentId: display list for this student
     * @return List of courses in grade card
     */
    public List<GradeCard> viewGradeCard(long semesterId, long studentId) throws SQLException;

    /**
     * Method to make payment  for   student and add to  database via card
     *
     * @param courseId: display list for this student
     * @param payment: display list for this student
     * @param studentId: display list for this student
     * @return boolean if payment is done
     */
    public boolean payfeesCard(long courseId, Payment payment, long studentId) throws SQLException ;



    }
