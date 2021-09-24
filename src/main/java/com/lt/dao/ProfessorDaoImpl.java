package com.lt.dao;

import com.lt.bean.Courses;
import com.lt.bean.Grade;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.constants.SqlConstants;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.GradeNotAddedException;
import com.lt.exception.ProfessorNotFoundException;
import com.lt.exception.StudentNotFoundException;
import com.lt.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * @author Implementation of Professor DAO Interface all methods to interacts with DB
 */
@Component
public class ProfessorDaoImpl implements ProfessorDaoInterface {
	
	 private static Logger logger = Logger.getLogger(ProfessorDaoImpl.class);
	 

    private static volatile ProfessorDaoImpl instance = null;
    public ProfessorDaoImpl() {
    }

    public static ProfessorDaoImpl getInstance() {
        if (instance == null) {
            synchronized (ProfessorDaoImpl.class) {
                instance = new ProfessorDaoImpl();
            }
        }
        return instance;
    }

    @Override
    public List<Courses> getCourseList(long professorId) throws SQLException,CourseNotAssignedToProfessorException {
        
        	
        
    	List<Courses> list = new ArrayList<Courses>();
        Connection con = null;
        PreparedStatement ps = null;
        String sql = SqlConstants.VIEW_COURSE;
        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (int) professorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Courses cs = new Courses();
                cs.setCourseId(rs.getLong(1));
                cs.setCourseName(rs.getString(2));
                cs.setCourseFee(rs.getDouble(3));
                cs.setCourseType(rs.getString(4));
                cs.setCourseDuration(rs.getString(5));
                cs.setCourseDetails(rs.getString(6));
                list.add(cs);
                
                
            }
		
		  if(list.isEmpty()) {
		  
		  throw new CourseNotAssignedToProfessorException(professorId); }
        }
		 
        catch (CourseNotAssignedToProfessorException e) {
        	logger.error(e.getMessage(professorId));
            }
        return list;
    }

    @Override
    public List<Courses> getListofRegCourses( long studentId,long semesterId) throws SQLException {
    	List<Courses> registeredCourseList = null;
    	try
        {
    	Connection con = null;
        PreparedStatement ps = null;
        con = DBUtil.getConnection();
        registeredCourseList = new ArrayList<Courses>();
        ps = con.prepareStatement(SqlConstants.LIST_REG_COURSES_SEM);
        ps.setInt(1, (int) studentId);
        ps.setInt(2, (int) semesterId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            long courseId = rs.getLong(1);
           String courseName = rs.getString(2);
           Courses cs = new Courses(courseId,courseName);
            registeredCourseList.add(cs);
        }
        if(registeredCourseList.isEmpty()) {
        	throw new StudentNotFoundException(studentId, semesterId);
        }
        }
        catch(StudentNotFoundException e)
        {
        	logger.error(e.getMessage(studentId,semesterId));
       
        }
        return registeredCourseList;
    }


    @Override

    public boolean addGrade(Grade grade) throws SQLException {
    	Connection con = null;
        PreparedStatement ps = null;
        con = DBUtil.getConnection();
        ps = con.prepareStatement(SqlConstants.ADD_GRADES);
        ps.setLong(1, grade.getCourseId());
        ps.setString(2, grade.getCourseName());
        ps.setLong(3, grade.getStudentId());
        ps.setLong(4, grade.getSemesterId());
        ps.setString(5, grade.getGrade());
        int flag = ps.executeUpdate();
        if(flag != 0) 
        	//logger.info(" Grade added  successfully for course id : "+ grade.getCourseId());
        	return true;
    	return false;
        
    }

    //List f students registered for a courses taught by the professor
    @Override
    public List<Student> getStudentList(long professorId) throws SQLException {
        List<Student> list = new ArrayList<Student>();
        Connection con = null;
        PreparedStatement ps = null;
        String sql = SqlConstants.VIEW_REG_STUDENT;
        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, (int) professorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt(1));
                student.setStudentName(rs.getString(2));
                student.setStudentEmail(rs.getString(3));
                student.setGender(rs.getString(4).charAt(0));
                student.setStudentDOB(rs.getDate(5));
                student.setContact_no(rs.getLong(6));
                student.setSemester_id(rs.getLong(7));
                list.add(student);
            }
            if(list.isEmpty()) {
            	throw new StudentNotFoundException(professorId);
            }
        } catch (StudentNotFoundException e) {
            logger.error(e.getMessage(professorId));
            
        }
        return list;
    }

    //get user data
    @Override
    public Professor getProfessorId(String username) throws ProfessorNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;
        Professor prof = null;
        long prof_id = 0L;
       
        try {
	 con = DBUtil.getConnection();
        ps = con.prepareStatement(SqlConstants.GET_PROFESSOR_DATA);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        	 prof_id = (long) rs.getInt(1);
             String prof_name = (String) rs.getString(2);
             prof = new Professor(prof_id,prof_name);
        }
        }
        catch(SQLException e) {
        	logger.error(e.getMessage());
        	throw new ProfessorNotFoundException(username);
        }
        return prof;
    }
}
