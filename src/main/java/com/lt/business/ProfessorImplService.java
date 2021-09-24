package com.lt.business;

import com.lt.bean.*;
import com.lt.dao.ProfessorDaoImpl;
import com.lt.dao.ProfessorDaoInterface;
import com.lt.dao.StudentDaoImpl;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.GradeNotAddedException;
import com.lt.exception.ProfessorNotFoundException;
import com.lt.exception.StudentNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessorImplService implements ProfessorInterface {
	private static Logger logger = Logger.getLogger(ProfessorImplService.class);

	boolean flag = false;

	@Autowired
	ProfessorDaoImpl pdo;

	@Override
	public List<Courses> viewFullCourses(long professorId) throws CourseNotAssignedToProfessorException, SQLException {

		List<Courses> courseList = pdo.getCourseList(professorId);

		return courseList;
	}

	@Override
	public boolean addGrade(Grade grade) throws SQLException, GradeNotAddedException {
		flag = pdo.addGrade(grade);
		return flag;
	}

	@Override
	public List<Courses> getListofRegCourses(long studentId, long semesterId)
			throws SQLException {
		List<Courses> studentList = pdo.getListofRegCourses(studentId, semesterId);
		return studentList;
	}

	@Override
	public List<Student> viewRegisteredStudents(long professorId) throws SQLException {
		List<Student> studentList = pdo.getStudentList(professorId);

		return studentList;
	}

	@Override
	public Professor getProfessorId(String username) throws SQLException, ProfessorNotFoundException {
		Professor prof = pdo.getProfessorId(username);
		return prof;
	}

}
