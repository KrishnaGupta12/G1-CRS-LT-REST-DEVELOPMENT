package com.lt.dao;

import com.lt.bean.Courses;
import com.lt.constants.SqlConstants;
import com.lt.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Implementation of DAO interface method to interacts with DB
 */
public class CourseDaoImpl implements CourseDaoInterface {
    Connection con = DBUtil.getConnection();
    PreparedStatement smt = null;
    List<Courses> availableList = null;


    /**
     * Student register for course
     */
    @Override
    public boolean registerCourse(long courseId, long semester_id) throws SQLException {
        return false;
    }


    /**
     * Provide list of available courses in catalog
     */
    @Override
    public List<Courses> showAvailableCourses(long semesterId) throws SQLException {
        availableList = new ArrayList<Courses>();
        smt = con.prepareStatement(SqlConstants.AVAILABLE_COURSES);
        smt.setInt(1, (int) semesterId);
        ResultSet rs = smt.executeQuery();
        while (rs.next()) {
            long course_id = rs.getInt(1);
            String course_name = rs.getString(2);
            double course_fees = rs.getDouble(3);
            String course_duration = rs.getString(4);
            String course_type = rs.getString(5);
            String course_details = rs.getString(6);
            Courses neWCourse = new Courses(course_id, course_name, course_fees, course_duration, course_details, course_type);
            availableList.add(neWCourse);
        }
        return availableList;
    }

}
