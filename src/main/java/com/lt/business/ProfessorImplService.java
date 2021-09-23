package com.lt.business;

import com.lt.bean.*;
import com.lt.dao.ProfessorDaoImpl;
import com.lt.dao.ProfessorDaoInterface;
import com.lt.dao.StudentDaoImpl;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.GradeNotAddedException;
import com.lt.exception.ProfessorNotFoundException;
import com.lt.exception.StudentNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessorImplService implements ProfessorInterface {
	 private static Logger logger = Logger.getLogger(ProfessorImplService.class);
	 
   boolean flag = false;
   
@Autowired	 
ProfessorDaoImpl pdo;
	@Override
    public void viewFullCourses(long professorId) throws CourseNotAssignedToProfessorException  {
    	
        try

        {
            courseList = pdo.getCourseList(professorId);

        
        //System.out.println(courseList);
            if(!courseList.isEmpty()) {
        System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","-----------","-----------","---------" ,"-------")) ;
        System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","COURSE ID","COURSE NAME","DETAILS","FEES"));
        System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","-----------","-----------","---------" ,"-------"));
        for (Courses c : courseList ){
            System.out.println(String.format("|%-11s | %-11s | %-11s| %-11s ",
                    c.getCourseId(),c.getCourseName(),c.getCourseDetails(),c.getCourseFee()));
        }
        }
        }
        catch(Exception e)
        {
        	logger.error(e.getMessage());
        }
		return courseList;
    }

    @Override
    public boolean addGrade(Grade grade) throws SQLException,GradeNotAddedException {
        ProfessorDaoImpl pdo = new ProfessorDaoImpl();
        flag = pdo.addGrade(grade);
       return flag;
    }

    @Override
    public List<Courses> getListofRegCourses( long studentId,long semesterId) throws SQLException,StudentNotFoundException{
        ProfessorDaoImpl pdo = new ProfessorDaoImpl();
        List<Courses> studentList = pdo.getListofRegCourses(studentId,semesterId);
        return studentList;
    }

    @Override
    public List<Student> viewRegisteredStudents(long professorId) throws SQLException, StudentNotFoundException {
    	List<Student> studentList  = null;
    	try
        {
         studentList = pdo.getStudentList(professorId);
         if(!studentList.isEmpty())
         {
        	 throw new StudentNotFoundException(professorId);
         }
        }
    	catch(StudentNotFoundException e)
    	{
    		logger.error(e.getMessage());
    	}
        return studentList;
    }

    @Override
    public Professor getProfessorId(String username) throws SQLException,ProfessorNotFoundException {
        // pdo = new ProfessorDaoImpl();
        Professor prof = pdo.getProfessorId(username);
        return prof;
    }
    
    

}
