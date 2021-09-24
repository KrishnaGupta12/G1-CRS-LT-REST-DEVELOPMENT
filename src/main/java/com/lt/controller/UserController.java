package com.lt.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lt.bean.Professor;
import com.lt.bean.Roles;
import com.lt.bean.Student;
import com.lt.bean.User;
import com.lt.business.ProfessorImplService;
import com.lt.business.StudentImplService;
import com.lt.business.UserImplService;
import com.lt.exception.ProfessorNotFoundException;
import com.lt.exception.StudentAlreadyRegisteredException;
import com.lt.exception.UserNotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 *  User Rest Controller with all rest apis
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	StudentImplService studentImplService;

	@Autowired
	UserImplService userImplService;

	@Autowired
	ProfessorImplService professorImplService;
	
/**
   * Method to login user
   *
   * @param User details
   * @return Response Entity with required status code
   */
	@ApiOperation(value = " User Login ", tags = "login")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 404, message = "Course not assigned to Professor ") })

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity verifyCredentials(@RequestBody User user)
			throws ValidationException, SQLException, UserNotFoundException, IOException, ProfessorNotFoundException {

		int roleId = userImplService.login(user.getUserName(), user.getUserPassword());
		Roles role = userImplService.getRoleDetails(roleId);

		if (roleId == 0) {
			throw new UserNotFoundException();
		} else {
			switch (roleId) {
			case 1:
				Student stud = studentImplService.getStudent(user.getUserName());
				return new ResponseEntity<>("Student Login Succesful", HttpStatus.OK);

			case 2:
				Professor pr = professorImplService.getProfessorId(user.getUserName());
				return new ResponseEntity<>("Professor Login Succesful", HttpStatus.OK);

			case 3:
				return new ResponseEntity<>("Admin Login Succesful", HttpStatus.OK);
			}
			return new ResponseEntity<>("Invalid User ...", HttpStatus.CONFLICT);
		}

	}
	
	/**
	   * Method to sign up student
	   *
	   * @param Student details
	   * @return Response Entity with required status code
	   */
	@ApiOperation(value = " Student Sign Up ", tags = "signup")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SignUp SuccessFul..!"),
			@ApiResponse(code = 404, message = "Student Already Registered") })

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity signUp(@RequestBody Student student) throws SQLException, StudentAlreadyRegisteredException {
		boolean flagStudentSignUp = studentImplService.signUp(student);
		if (flagStudentSignUp) {
			return new ResponseEntity<>("SignUp SuccessFul..!", HttpStatus.OK);
		} else {
			throw new StudentAlreadyRegisteredException();
		}
	}
}