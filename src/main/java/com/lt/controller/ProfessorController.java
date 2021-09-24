package com.lt.controller;

import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lt.bean.Courses;
import com.lt.bean.Grade;
import com.lt.bean.Student;
import com.lt.business.ProfessorImplService;
import com.lt.exception.CourseDetailsNotFoundException;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.GradeNotAddedException;
import com.lt.exception.StudentDetailsNotFoundException;
import com.lt.exception.StudentNotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 *  Professor Rest Controller with all rest apis for professorS 
 */
@RestController
@RequestMapping("/professor")
@CrossOrigin
public class ProfessorController {

	@Autowired
	ProfessorImplService professorImplService;

	private static Logger logger = Logger.getLogger(ProfessorController.class);
	
	
	/**
     * Method to show list of courses teach by professor student from database
     *
     * @param professorId: professor id for which list of courses should be displayed
     * @return ResponseEntity with proper status code
     * @exception CourseNotAssignedToProfessorException
     */

	@ApiOperation(value = " View Assigned Courses ", tags = "viewcourse")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 409, message = "Course not assigned to Professor ") })

	@RequestMapping(value = "/viewcourse/{professorId}", method = RequestMethod.GET)
	public ResponseEntity<List<Courses>> viewCourses(@PathVariable("professorId") long professorId)
			throws SQLException, CourseNotAssignedToProfessorException {
		List<Courses> list = professorImplService.viewFullCourses(professorId);

		if (list.size() == 0) {
			throw new CourseNotAssignedToProfessorException();
		}
		logger.info("List of Courses for Professor with ID :" + professorId);
		return ResponseEntity.of(Optional.of(list));
	}

	
	
	/**
     * Method to show list of students from database
     *
     * @param studentId:  studentId for which display list of students
     * @param semesterId: semesterId list for this student
     * @return ResponseEntity with proper status code
     * @exception StudentNotFoundException
     */
	@ApiOperation(value = " View Registered Students under Professor ", tags = "viewregisteredtudents")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "No registered students found  ..! ") })
	@RequestMapping(value = "/viewregisteredtudents/{professorId}", method = RequestMethod.GET)

	public ResponseEntity<List<Student>> viewRegisterStudents(@PathVariable("professorId") long professorId)
			throws SQLException, StudentNotFoundException {
		List<Student> list = professorImplService.viewRegisteredStudents(professorId);

		if (list.size() == 0) {
			throw new StudentNotFoundException();
		}
		logger.info("Students register under Professor with ID :" + professorId);
		return ResponseEntity.of(Optional.of(list));
	}
	
	
	/**
     * Method to show list of courses registered for student
     *
     * @param professor_id: professor id for which the student to be displayed
     * @return ResponseEntity with proper status code
     * @exception CourseDetailsNotFoundException
     */
	@ApiOperation(value = " Course List for student ", tags = "courselist")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "Course not found  ..! ") })
	@RequestMapping(value = "/courselist/{student_id}/{semesterId}", method = RequestMethod.GET)
	public ResponseEntity showCourseList(@PathVariable("student_id") long studentId,
			@PathVariable("semesterId") long semesterId)
			throws SQLException, CourseDetailsNotFoundException {
		List<Courses> studentList = professorImplService.getListofRegCourses(studentId, semesterId);

		if (studentList.size() == 0) {
			throw new CourseDetailsNotFoundException();
		}
		logger.info("Student with Id " + studentId + " registered Courses");
		return ResponseEntity.of(Optional.of(studentList));
	}
	
	

    /**
     * Method to add grade to database
     *
     * @param grade: Grade to be added for course
     * @return ResponseEntity with proper status code
     * @exception GradeNotAddedException
     */
	@ApiOperation(value = " Add Grade ", tags = "addgrade")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Grade added Successfully..!"),
			@ApiResponse(code = 409, message = "  Grade can't be added..! ") })
	@RequestMapping(value = "/addgrade", method = RequestMethod.POST)
	public ResponseEntity addgrade(@RequestBody Grade grade) throws SQLException, GradeNotAddedException {
		boolean gradeFlag = professorImplService.addGrade(grade);

		if (!gradeFlag) {
			throw new GradeNotAddedException();
		}
		return new ResponseEntity<>("Grade added Successfully..!", HttpStatus.OK);

	}
}
