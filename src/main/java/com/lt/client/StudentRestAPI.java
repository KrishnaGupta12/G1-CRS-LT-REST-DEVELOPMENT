package com.lt.client;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lt.bean.Courses;
import com.lt.bean.GradeCard;
import com.lt.bean.Payment;
import com.lt.bean.RegisterCourse;
import com.lt.business.StudentImplService;
import com.lt.business.UserImplServiceInterface;
import com.lt.constants.ModeOfPayment;
import com.lt.dao.StudentDaoImpl;
import com.lt.exception.CourseAlreadyRegisteredException;
import com.lt.exception.CourseDetailsNotFoundException;

@RestController
@RequestMapping("/Student")
@CrossOrigin //@responseststaus @exceptionhandler
public class StudentRestAPI {

	private static Logger logger = Logger.getLogger(StudentRestAPI.class);
	
	@Autowired
	StudentImplService studentImplService;

	@Autowired
	UserImplServiceInterface userImplService;

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

	@RequestMapping(value = "/removecourse/{student_id}/{semester_id}/{course_id}", method = RequestMethod.POST)
	public ResponseEntity removecourse(@PathVariable long student_id, @PathVariable long course_id,
			@PathVariable long semester_id) throws SQLException {

		Set<RegisterCourse> list = studentImplService.viewRegisteredCourses(student_id, semester_id);
		if (studentImplService.checkId(course_id, list)) {
			boolean status = studentImplService.removeCourse(course_id);
			if (status)
				return new ResponseEntity<>("Course Deleted Succesfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid CourseId...", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	
	@RequestMapping(value = "/viewregistercourse/{student_id}/{semester_id}", method = RequestMethod.GET)
	public ResponseEntity<Set<RegisterCourse>> viewRegisteredCourse(@PathVariable long student_id,
			@PathVariable long semester_id) throws SQLException, CourseDetailsNotFoundException {

		Set<RegisterCourse> list = studentImplService.viewRegisteredCourses(student_id, semester_id);
		if(list.size() ==0) {
			throw new CourseDetailsNotFoundException();
			}
		return ResponseEntity.of(Optional.of(list));
	}

	@RequestMapping(value = "/paymentlist/{student_id}", method = RequestMethod.GET)
	public ResponseEntity showPaymentList(@PathVariable long student_id) throws SQLException {

		Set<RegisterCourse> pendingPaymentList = studentImplService.showListofPendingPayment(student_id);

		if (pendingPaymentList.size() == 0) {
			return new ResponseEntity<>("No courses pending for payment", HttpStatus.OK);
		} else {
			return ResponseEntity.of(Optional.of(pendingPaymentList));

		}
	}

	@RequestMapping(value = "/payfeecash/{student_id}/{course_id}/{mode}/{amount}", method = RequestMethod.POST)
	public ResponseEntity payFeeCash(@PathVariable long student_id, @PathVariable String mode,
			@PathVariable double amount, @PathVariable long course_id) throws SQLException {

		// ModeOfPayment modePayment = ModeOfPayment.getModeofPayment(mode);
		long transactionId = Long.parseLong(studentImplService.generateTransactionId());

		Payment payment = new Payment(amount, mode, transactionId);
		boolean paymentFlag = studentImplService.payfees(course_id, payment, student_id);

		if (paymentFlag) {
			System.out.println("Payment Successful..!");
			return new ResponseEntity<>("Payment Successful..!", HttpStatus.OK);
		} else {
			System.out.println("Payment failed..!");
			return new ResponseEntity<>("Payment failed..!", HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/payfeecard/{student_id}/{course_id}/{mode}/{card_no}/{expiry}/{amount}", method = RequestMethod.POST)
	public ResponseEntity payFeeCard(@PathVariable long student_id, @PathVariable String mode,
			@PathVariable String card_no, @PathVariable String expiry, @PathVariable double amount,
			@PathVariable long course_id) throws SQLException {

		// ModeOfPayment modePayment = ModeOfPayment.getModeofPayment(mode);
		long transactionId = Long.parseLong(studentImplService.generateTransactionId());

		Payment payment = new Payment(transactionId, amount, mode, card_no, expiry);
		boolean paymentFlag = studentImplService.payfeesCard(course_id, payment, student_id);

		if (paymentFlag) {
			System.out.println("Payment Successful..!");
			return new ResponseEntity<>("Payment Successful..!", HttpStatus.OK);
		} else {
			System.out.println("Payment failed..!");
			return new ResponseEntity<>("Payment failed..!", HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/viewgradecard/{student_id}/{semester_id}", method = RequestMethod.GET)
	public ResponseEntity viewGradeCard(@PathVariable long student_id, @PathVariable long semester_id)
			throws SQLException {

		List<GradeCard> viewGradeCard = studentImplService.viewGradeCard(semester_id, student_id);

		if (viewGradeCard.size() == 0) {
			return new ResponseEntity<>("No Grade Card Generated", HttpStatus.OK);
		} else {
			return ResponseEntity.of(Optional.of(viewGradeCard));

		}
	}

}
