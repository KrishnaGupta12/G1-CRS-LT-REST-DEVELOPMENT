package com.lt.controller;

import com.lt.bean.Courses;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.bean.User;
import com.lt.business.AdminImplService;
import com.lt.dao.AdminDaoImpl;
import com.lt.exception.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin

public class AdminController {

	private static Logger logger = Logger.getLogger(StudentController.class);

	@Autowired
	AdminImplService adminImplService;

	@ApiOperation(value = "Add Course Details ", tags = "addCourseDetails")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Course Added"),
			@ApiResponse(code = 409, message = "Courses Already Existed") })
	@RequestMapping(value = "/addCourseDetails", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addCourse(@RequestBody Courses courses) throws SQLException, CourseExistedException {

		boolean flag = adminImplService.addCourse(courses);
		if (!flag) {
			throw new CourseExistedException();
		}
		return new ResponseEntity("Course Added", HttpStatus.OK);

	}

	@ApiOperation(value = "View all Courses ", tags = "viewAllCourses")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 409, message = "Course Not Found") })
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewAllCourses")
	public ResponseEntity adminViewAllCourses() throws SQLException, CourseNotFoundException {

		List<Courses> coursesList = adminImplService.adminViewAllCourses();
		if (coursesList.size() == 0) {
			throw new CourseNotFoundException();
		}
		return ResponseEntity.of(Optional.of(coursesList));
	}

	@ApiOperation(value = "Remove Course ", tags = "deleteCourse")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Course Found & Deleted successfully"),
			@ApiResponse(code = 404, message = "Course Not Found") })
	@RequestMapping(value = "/deleteCourse/{course_id}", method = RequestMethod.POST)
	public ResponseEntity deletecourse(@PathVariable long course_id) throws SQLException, IOException {

		List<Courses> list = adminImplService.adminViewAllCourses();
		boolean status = adminImplService.deleteCourse(course_id, list);
		if (!status) {
			logger.info("Course Deleted successfully!! ");
			return new ResponseEntity<>("Course Found & Deleted successfully", HttpStatus.OK);
		}

		return new ResponseEntity<>("Course Not Found -", HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Pending Approval Student List ", tags = "pendingapproval")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "No Students Pending for Approval") })

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/pendingapproval")
	public ResponseEntity pendingApproval() throws SQLException {

		List<Student> studList = adminImplService.showListOfPendingStudent();
		if (studList.isEmpty()) {
			return new ResponseEntity<>("No Students Pending for Approval", HttpStatus.NOT_FOUND);

		}
		return ResponseEntity.of(Optional.of(studList));
	}

	@ApiOperation(value = " Approve Student  ", tags = "approvestudent")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Student Approved successfully"),
			@ApiResponse(code = 404, message = "No Students Pending for Approval") })
	@RequestMapping(value = "/approvestudent/{studentid}", method = RequestMethod.POST)
	public ResponseEntity approveStudent(@PathVariable int studentid)
			throws SQLException, StudentDetailsNotFoundException {

		boolean status = adminImplService.approveStudent(studentid);
		return new ResponseEntity<>("Student Approved successfully", HttpStatus.OK);
	}

	@ApiOperation(value = " Generate Report Card ", tags = "generatereportcard")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Report Card Generated successfully"),
			@ApiResponse(code = 409, message = "Report Card Not Generated") })
	@RequestMapping(value = "/generatereportcard", method = RequestMethod.GET)
	public ResponseEntity generateReportCard() throws SQLException {
		boolean status = adminImplService.generateReportCard();
		if (!status) {
			return new ResponseEntity<>("Report Card Not Generated", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("Report Card Generated successfully", HttpStatus.OK);

	}

	@ApiOperation(value = " Add Professor ", tags = "addprofessor")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Professor Added successfully"),
			@ApiResponse(code = 409, message = "Professor Not Added") })
	@RequestMapping(value = "/addprofessor", method = RequestMethod.POST)
	public ResponseEntity addProfessor(@RequestBody Professor professor) throws SQLException {

		boolean status = adminImplService.addProfessor(professor);
		if (!status) {
			return new ResponseEntity<>("Professor Not Added", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("Professor Added successfully", HttpStatus.OK);

	}
}