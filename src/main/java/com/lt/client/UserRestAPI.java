package com.lt.client;

import java.io.IOException;
import java.sql.SQLException;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lt.bean.Professor;
import com.lt.bean.Roles;
import com.lt.bean.Student;
import com.lt.exception.ProfessorNotFoundException;
import com.lt.exception.RoleNotFoundException;
import com.lt.exception.StudentDetailsNotFoundException;

import com.lt.exception.UserNotFoundException;
import com.lt.business.ProfessorImplService;
import com.lt.business.StudentImplService;

import com.lt.business.UserInterface;

import com.lt.business.UserImplServiceInterface;



@RestController
@RequestMapping("/user")
public class UserRestAPI {

	@Autowired
	StudentImplService studentImplService;

	@Autowired
	UserImplServiceInterface userImplService;

	@Autowired
	ProfessorImplService professorImplService;

	@RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.POST)

	public ResponseEntity verifyCredentials(@PathVariable String username, @PathVariable String password)
			throws ValidationException, SQLException, UserNotFoundException, IOException, ProfessorNotFoundException {

			int roleId = userImplService.login(username, password);
			Roles role = userImplService.getRoleDetails(roleId);
			
			if (roleId == 0){
				throw new UserNotFoundException(username, password);
			}
			else {
				switch (roleId) {
				case 1:
					Student stud = studentImplService.getStudent(username);
					return new ResponseEntity<>("Student Login Succesful", HttpStatus.OK);

				case 2:
					Professor pr = professorImplService.getProfessorId(username);
					return new ResponseEntity<>("Professor Login Succesful", HttpStatus.OK);

				case 3:
					return new ResponseEntity<>("Admin Login Succesful", HttpStatus.OK);
				}
				return new ResponseEntity<>("Login Succesfull ...", HttpStatus.OK);
			} 
 
	}

	@RequestMapping(value = "/signup",method = RequestMethod.POST)
	public ResponseEntity signUp(@RequestBody Student student) throws SQLException{
     boolean flagStudentSignUp = studentImplService.signUp(student);
     if (flagStudentSignUp) {
         return new ResponseEntity<>("SignUp SuccessFul..!", HttpStatus.OK);
     } else {
         return new ResponseEntity<>("Signup Failed..!", HttpStatus.OK);
     }
 }
}