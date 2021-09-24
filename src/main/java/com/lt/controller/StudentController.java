package com.lt.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.lt.bean.GradeCard;
import com.lt.bean.Payment;
import com.lt.bean.RegisterCourse;
import com.lt.business.StudentImplService;
import com.lt.business.UserImplService;
import com.lt.exception.CourseAlreadyRegisteredException;
import com.lt.exception.CourseDetailsNotFoundException;

import io.swagger.annotations.*;

/**
 *  Student Rest Controller with all rest apis for student 
 */

@RestController
@RequestMapping("/Student")
@CrossOrigin
public class StudentController {
	

	private static Logger logger = Logger.getLogger(StudentController.class);

	@Autowired
	StudentImplService studentImplService;

	@Autowired
	UserImplService userImplService;

	
	/**
     * Method to show list of available course to  student from database
     *
     * @param semesterId: semeseter for which list need to be displayed
     * @return ResponseEntity with proper status code
     * @exception CourseDetailsNotFoundException
     */
	@ApiOperation(value = "Show Available Courses ", tags = "availablecourse")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "Course details not found") })

	@RequestMapping(value = "/availablecourse/{semester_id}", method = RequestMethod.GET)
	public ResponseEntity showAvailableCourses(@PathVariable long semester_id)
			throws SQLException, CourseDetailsNotFoundException {

		List<Courses> list = studentImplService.showAvailableCourses(semester_id);
		if (list.size() == 0) {
			throw new CourseDetailsNotFoundException();
		}
		return ResponseEntity.of(Optional.of(list));
	}

	
	
	
	 /**
     * Method to add registered courses for student
     *
     * @param student_id: student id who wants to register
     * @param semesterid: for which semserter
     * @param courseId:   for which course
     * @return ResponseEntity with proper status code
     * @exception CourseAlreadyRegisteredException
     */
	@ApiOperation(value = "Register Course ", tags = "registercourse")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 409, message = "Course Already Registered") })

	@RequestMapping(value = "/registercourse/{student_id}/{semester_id}/{course_id}", method = RequestMethod.POST)
	public ResponseEntity registerCourse(@PathVariable long student_id, @PathVariable long course_id,
			@PathVariable long semester_id) throws SQLException, CourseAlreadyRegisteredException {

		boolean flag = studentImplService.registerForCourse(student_id, semester_id, course_id);
		if (!flag) {
			throw new CourseAlreadyRegisteredException();
		}
		logger.info("Course Registered Succesfully");
		return new ResponseEntity<>("Course Registered Succesfully", HttpStatus.OK);
	}

	

    /**
     * Method to remove course from database
     *
     * @param courseId: student id who wants to register
     * @return ResponseEntity with proper status code
     * @exception CourseDetailsNotFoundException
     */
	@ApiOperation(value = "Remove Course ", tags = "removecourse")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 409, message = "Course Can't be Deleted") })
	
	@RequestMapping(value = "/removecourse/{student_id}/{semester_id}/{course_id}", method = RequestMethod.POST)
	public ResponseEntity removecourse(@PathVariable long student_id, @PathVariable long course_id,
			@PathVariable long semester_id) throws SQLException, CourseDetailsNotFoundException {

		Set<RegisterCourse> list = studentImplService.viewRegisteredCourses(student_id, semester_id);
		if (studentImplService.checkId(course_id, list)) {
			boolean status = studentImplService.removeCourse(course_id);
			if (!status) {
				logger.info("Course Can't be Deleted ");
				return new ResponseEntity<>("Course Can't be Deleted", HttpStatus.CONFLICT);
			}
		}
		logger.info("Course Deleted Succesfully");
		return new ResponseEntity<>("Course Deleted Succesfully", HttpStatus.OK);
	}
	
	
	
	/**
     * Method to list registered courses for student
     *
     * @param studentId:  student id who wants to register
     * @param semesterId: for which semserter
     * @return ResponseEntity with proper status code
     * @exception CourseDetailsNotFoundException
     */
	@ApiOperation(value = "View Registered Courses ", tags = "viewregistercourse")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "Course deatails not found ") })
	@RequestMapping(value = "/viewregistercourse/{student_id}/{semester_id}", method = RequestMethod.GET)
	public ResponseEntity<Set<RegisterCourse>> viewRegisteredCourse(@PathVariable long student_id,
			@PathVariable long semester_id) throws SQLException, CourseDetailsNotFoundException {

		Set<RegisterCourse> list = studentImplService.viewRegisteredCourses(student_id, semester_id);
		if (list.size() == 0) {
			throw new CourseDetailsNotFoundException();
		}
		return ResponseEntity.of(Optional.of(list));
	}

	
	/**
     * Method to show list of available course to  student from database
     *
     * @param ResponseEntity with proper status code
     * @return some set  of pending payments
     */
	@ApiOperation(value = "Show Pending Payment List of Courses ", tags = "paymentlist")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "No courses pending for payment") })

	@RequestMapping(value = "/paymentlist/{student_id}", method = RequestMethod.GET)
	public ResponseEntity showPaymentList(@PathVariable long student_id) throws SQLException {

		Set<RegisterCourse> pendingPaymentList = studentImplService.showListofPendingPayment(student_id);

		if (pendingPaymentList.size() == 0) {
			logger.info("No courses pending for payment");
			return new ResponseEntity<>("No courses pending for payment", HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.of(Optional.of(pendingPaymentList));

		}
	}
	

	/**
     * Method to make payment  for   student and add to  database
     *
     * @param courseId: display list for this student
     * @param payment: display list for this student
     * @param studentid: display list for this student
     * @return ResponseEntity with proper status code
     */
	@ApiOperation(value = "Pay fee via cash ", tags = "payfeecash")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Payment Successful..!"),
			@ApiResponse(code = 409, message = "Payment failed..!") })

	@RequestMapping(value = "/payfeecash/{student_id}/{course_id}", method = RequestMethod.POST)
	public ResponseEntity payFeeCash(@PathVariable long student_id, @PathVariable long course_id,
			@RequestBody Payment payment) throws SQLException {

		boolean paymentFlag = studentImplService.payfees(course_id, payment, student_id);

		if (paymentFlag) {
			logger.info("Payment Successful..!");
			return new ResponseEntity<>("Payment Successful..!", HttpStatus.OK);
		} else {
			logger.info("Payment failed..!");
			return new ResponseEntity<>("Payment failed..!", HttpStatus.CONFLICT);
		}
	}

	
	 /**
     * Method to make payment  for   student and add to  database via card
     *
     * @param courseId: display list for this student
     * @param payment: display list for this student
     * @param studentId: display list for this student
     * @return ResponseEntity with proper status code
     */
	@ApiOperation(value = "Pay fee via card ", tags = "payfeecard")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Payment Successful..!"),
			@ApiResponse(code = 409, message = "Payment failed..!") })

	@RequestMapping(value = "/payfeecard/{student_id}/{course_id}", method = RequestMethod.POST)
	public ResponseEntity payFeeCard(@PathVariable long student_id, @PathVariable long course_id,
			@RequestBody Payment payment) throws SQLException {

		boolean paymentFlag = studentImplService.payfeesCard(course_id, payment, student_id);

		if (paymentFlag) {
			logger.info("Payment Successful..!");
			return new ResponseEntity<>("Payment Successful..!", HttpStatus.OK);
		} else {
			logger.info("Payment failed..!");
			return new ResponseEntity<>("Payment failed..!", HttpStatus.CONFLICT);
		}
	}
	
	
	/**
     * Method to show list of available course to  student from database
     *
     * @param semesterId: display list for this student
     * @param studentId: display list for this student
     * @return ResponseEntity with proper status code
     */
	@ApiOperation(value = "View Grade Card ", tags = "viewgradecard")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok|Success"),
			@ApiResponse(code = 404, message = "No Grade Card Generated") })

	@RequestMapping(value = "/viewgradecard/{student_id}/{semester_id}", method = RequestMethod.GET)
	public ResponseEntity viewGradeCard(@PathVariable long student_id, @PathVariable long semester_id)
			throws SQLException {

		List<GradeCard> viewGradeCard = studentImplService.viewGradeCard(semester_id, student_id);

		if (viewGradeCard.size() == 0) {
			logger.info("No Grade Card Generated");
			return new ResponseEntity<>("No Grade Card Generated", HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.of(Optional.of(viewGradeCard));

		}
	}

}
