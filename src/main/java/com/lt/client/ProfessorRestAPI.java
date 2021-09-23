package com.lt.client;

import java.sql.SQLException;

import java.util.List;
import java.util.Optional;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lt.bean.Courses;
import com.lt.bean.Grade;
import com.lt.bean.Student;
import com.lt.business.ProfessorImplService;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.GradeNotAddedException;
import com.lt.exception.StudentNotFoundException;

@RestController
@RequestMapping("/professor")
@CrossOrigin // @responsestatus @exceptionhandler
public class ProfessorRestAPI {

	@Autowired
	ProfessorImplService professorImplService;

	private static Logger logger = Logger.getLogger(ProfessorRestAPI.class);

	@RequestMapping(value = "/viewcourse/{professorId}", method = RequestMethod.GET)
	// @GetMapping("/viewcourse/{professorId}")
	//@ExceptionHandler({ CourseNotAssignedToProfessorException.class })
	public ResponseEntity<List<Courses>> viewCourses(@PathVariable("professorId") long professorId)
			throws SQLException, CourseNotAssignedToProfessorException {
		System.out.println("Inside Professor Controller: view course");
		List<Courses> list = null;
			list = professorImplService.viewFullCourses(professorId);
			if (list.size() == 0) {
				throw new CourseNotAssignedToProfessorException();
			}
				logger.info("List of Courses for Professor with ID :" +professorId);
				 return ResponseEntity.of(Optional.of(list));
	}
				
				
				
			

	@RequestMapping(value = "/viewregisterstudents/{professorId}", method = RequestMethod.GET)
	
	public ResponseEntity<List<Student>> viewRegisterStudents(@PathVariable("professorId") long professorId)
			throws SQLException, StudentNotFoundException {
			List<Student> list = null;
			list = professorImplService.viewRegisteredStudents(professorId);
			if (list.size() == 0)
			{
				throw new StudentNotFoundException(professorId);
			}
			logger.info("Students register under Professor with ID :" +professorId);
			return ResponseEntity.of(Optional.of(list));
	}
	
	

	@RequestMapping(value = "/studentlist/{student_id}/{semesterId}", method = RequestMethod.GET)
	public ResponseEntity showCourseList(@PathVariable("student_id") long studentId,
			@PathVariable("semesterId") long semesterId) throws SQLException, StudentNotFoundException {
		List<Courses> studentList = null;
		studentList = professorImplService.getListofRegCourses(studentId, semesterId);
			if (studentList.size() == 0) {
				throw new StudentNotFoundException(studentId, semesterId);
			}
			logger.info("Student with Id " +studentId+ " registered Courses");
			return ResponseEntity.of(Optional.of(studentList));
	}
	
	

	@RequestMapping(value = "/addgrade/{courseId}/{coursename}/{student_id}/{semesterId}/{grade}", method = RequestMethod.POST)
	public ResponseEntity addgrade(@PathVariable("courseId") long courseId,
			@PathVariable("coursename") String coursename, @PathVariable("student_id") long studentId,
			@PathVariable("semesterId") long semesterId, @PathVariable("grade") String grade)
			throws SQLException, GradeNotAddedException {
		Grade gradeObj = new Grade(courseId, coursename, studentId, semesterId, grade);
		// Student student = new Student(studentId,semesterId);
		boolean gradeFlag = false;
			gradeFlag = professorImplService.addGrade(gradeObj);
			if (gradeFlag != true) {
				throw new GradeNotAddedException(studentId);
				}
		return new ResponseEntity<>("Grade added Successfully..!", HttpStatus.OK);

	}
}
