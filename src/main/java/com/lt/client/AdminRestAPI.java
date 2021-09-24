package com.lt.client;

import com.lt.bean.Courses;
import com.lt.business.AdminImplService;
import com.lt.dao.AdminDaoImpl;
import com.lt.exception.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin

public class AdminRestAPI {

    private static Logger logger = Logger.getLogger(StudentRestAPI.class);

    @Autowired
    AdminImplService adminImplService;
    @Autowired
    AdminDaoImpl adminDao;

    @RequestMapping(value = "/addCourseDetails", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addCourse(@RequestBody Courses courses) throws SQLException, CourseExistedException {
     try{
         boolean flag = adminImplService.addCourse(courses);

         if(!flag) {
             throw new CourseExistedException();
         }
     }catch(SQLException e)
        {
            logger.error(e.getMessage());


        }return new ResponseEntity("Course Not Added",HttpStatus.EXPECTATION_FAILED);

    }


    @RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/viewAllCourses")
    public List<Courses> adminViewAllCourses() throws SQLException {
       try {
           List<Courses> coursesList = adminImplService.adminViewAllCourses();
           if (coursesList != null) {
               return coursesList;
           } else
               throw new CourseNotFoundException();
       }catch (CourseNotFoundException e){
           logger.info(e.getMessage());
       }
        return null;
    }

    @RequestMapping(value = "/deleteCourse/{course_id}", method = RequestMethod.POST)
    public ResponseEntity deletecourse(@PathVariable long course_id) throws SQLException, IOException {

        List<Courses> list = adminImplService.adminViewAllCourses();
        if (adminImplService.checkId(course_id,list)) {
            boolean status = adminImplService.deleteCourse(course_id,list);
            if (!status) {
                logger.info("Course Deleted successfully!! ");
                return new ResponseEntity<>("Course Found & Deleted successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Course Not Found -", HttpStatus.NOT_FOUND);
    }


}


