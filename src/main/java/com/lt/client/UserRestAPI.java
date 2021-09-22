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

/**
 * 
 * 
 * @author dilpreetkaur
 *
 * 
 * 
 * 
 * 
 */

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

		//try

		//{
			int roleId = userImplService.login(username, password);
			if (roleId != 0) {
				Roles role = userImplService.getRoleDetails(roleId);

				switch (roleId) {
				case 1:
					Student stud = studentImplService.getStudent(username);
					// return ResponseEntity.status(HttpStatus.ACCEPTED).build()
					return new ResponseEntity<>("Student Login Succesful", HttpStatus.OK);

				case 2:
					Professor pr = professorImplService.getProfessorId(username);
					return new ResponseEntity<>("Professor Login Succesful", HttpStatus.OK);

				case 3:
					return new ResponseEntity<>("Admin Login Succesful", HttpStatus.OK);
				}

			} else

			{

				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

			}

//		} catch (RoleNotFoundException e)
//
//		{
//
//			return new ResponseEntity<>("Role Not found", HttpStatus.NOT_FOUND);
//		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
 
	}

//	@POST
//
//	@Path("/studentRegistration")
//
//	@Consumes(MediaType.APPLICATION_JSON)
//
//	public Response register(@Valid Student student)
//
//	{
//
//		try
//
//		{
//
//			studentInterface.register(student.getName(), student.getUserId(), student.getPassword(),
//					student.getGender(), student.getBatch(), student.getBranchName(), student.getAddress(),
//					student.getCountry());
//
//		} catch (Exception ex)
//
//		{
//
//			return Response.status(500).entity("Something went wrong! Please try again.").build();
//
//		}
//
//		return Response.status(201).entity("Registration Successful for " + student.getUserId()).build();
//
//	}

}