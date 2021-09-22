package com.lt.dao;

import com.lt.bean.CourseCatalog;
import com.lt.bean.Courses;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Course DAO Interface for getting list of register courses
 */
public interface CourseDaoInterface {

    /**
     * Method to  registered courses for student
     *
     * @param student_id: student id who wants to register
     * @param semesterid: for which semserter
     * @param courseId:   for which course
     * @return some boolean  if student is added
     */
    public boolean registerCourse(long course_id, long semester_id) throws SQLException;

    public List<Courses> showAvailableCourses(long semesterId) throws SQLException;


}
