package com.lt.client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lt.bean.Courses;
import com.lt.bean.Student;
import com.lt.business.ProfessorImplService;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.StudentNotFoundException;

@RestController
@RequestMapping("/professor")
public class ProfessorRestAPI {
	
	 @Autowired
		ProfessorImplService professorImplService;
	
	 private static Logger logger = Logger.getLogger(ProfessorRestAPI.class);
	
	
	@RequestMapping(value="/viewcourse/{professorId}",method=RequestMethod.GET)
	//@GetMapping("/viewcourse/{professorId}")
	 public ResponseEntity <List<Courses>> viewCourses(@PathVariable("professorId") long professorId) throws SQLException
	{
		List <Courses> list = null;
		try {
			  list = professorImplService.viewFullCourses(professorId);
			  
			  System.out.println(list);
			 if(list.size()==0)
			 {
				 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				 
			 }
			
		} catch (CourseNotAssignedToProfessorException e) {
			
			logger.error(e.getMessage());
			
			//return new ResponseEntity<>("List of Courses not available", HttpStatus.NOT_FOUND);
		}
		 return ResponseEntity.of(Optional.of(list));
	}
	
	
	@RequestMapping(value="/viewregisterstudents/{professorId}",method=RequestMethod.GET)
	//@GetMapping("/viewcourse/{professorId}")
	 public ResponseEntity <List<Student>> viewRegisterStudents(@PathVariable("professorId") long professorId) throws SQLException
	{
		List<Student> list = null;
		try {
			  list = professorImplService.viewRegisteredStudents(professorId);
			  
			  System.out.println(list);
			 if(list.size()==0)
			 {
				 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				 
			 }
			
		} catch (StudentNotFoundException e) {
			
			logger.error(e.getMessage());
			
			//return new ResponseEntity<>("List of Courses not available", HttpStatus.NOT_FOUND);
		}
		 return ResponseEntity.of(Optional.of(list));
		
		
	}
	
	 /* @RequestMapping(value="/hello/{name}") public ResponseEntity <List<String>>
	 * hello(@PathVariable("name") String name ) { List list = new ArrayList();
	 * //list.add(name); //list.add("sneha"); if(list.size()==0) { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } return
	 * ResponseEntity.of(Optional.of(list)); }
	 */
}

