package com.lt.client;

import com.lt.bean.*;
import com.lt.business.ProfessorImplService;
import com.lt.business.ProfessorInterface;
import com.lt.business.StudentImplService;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.GradeNotAddedException;
import com.lt.exception.StudentNotFoundException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * @author Professor Individual Menu with all the professor functionality
 */
public class ProfessorMenu {
    public void professorSession(String userName, long professorId, String professorName, String loginTime) throws SQLException, CourseNotAssignedToProfessorException, StudentNotFoundException, GradeNotAddedException {
        System.out.println("Welcome "+professorName+" to your panel. Have a Good day!!");
        System.out.println("Login at : "+loginTime);
        System.out.println("------------------------------------------------------------");
        ProfessorImplService professorImplService = new ProfessorImplService();

        Boolean permission = true;
        while (permission) {
            System.out.println("Choose your Tasks");
            System.out.println("1. View List of Courses");
            System.out.println("2. Add grades");
            System.out.println("3. View Registered courses");
            System.out.println("4. Logout");
            Scanner sc = new Scanner(System.in);
            int task = sc.nextInt();
            switch (task) {
                case 1:
                    System.out.println("View Your Courses");
                    professorImplService.viewFullCourses(professorId);
                    break;

                case 2:
                    System.out.println("Add grades");
                    System.out.println("Enter Semester Id : ");
                    int semesterId = sc.nextInt();
                    System.out.println("Enter StudentId : ");
                    long studentId = sc.nextLong();
                    List<Courses>  registeredStudentList = professorImplService.getListofRegCourses(studentId,semesterId);
                    if(!registeredStudentList.isEmpty()) {
                        System.out.println(String.format("|%-10s | %-10s |", "-----------", "-----------"));
                        System.out.println(String.format("|%-10s | %-10s |", "COURSE ID", "COURSE NAME"));
                        System.out.println(String.format("|%-10s | %-10s |", "-----------", "-----------"));
                        for (Courses c : registeredStudentList) {
                            System.out.println(String.format("|%-11s | %-11s|  ",
                                    c.getCourseId(), c.getCourseName()));
                        }
                        for (Courses c : registeredStudentList) {
                            System.out.println("Enter CourseId : ");
                            long courseId = (int) sc.nextLong();
                            System.out.println("Enter Grade : ");
                            String grade = sc.next();
                            Grade gradeObj = new Grade(c.getCourseId(), c.getCourseName(), studentId, semesterId, grade);
                            professorImplService.addGrade(gradeObj);
                        }
                    }
                    break;

                case 3:
                    System.out.println("View Registered Students");
                    List<Student> studList = professorImplService.viewRegisteredStudents(professorId);
                   if(studList.isEmpty()) {
                       System.out.println(String.format("|%-10s | %-10s | %-10s|", "-----------", "-----------", "--------------"));
                       System.out.println(String.format("|%-10s | %-10s | %-10s|", "STUD ID", "STUD NAME", "SEMESTER ID"));
                       System.out.println(String.format("|%-10s | %-10s | %-10s|", "-----------", "-----------", "--------------"));
                       for (Student c : studList) {
                           System.out.println(String.format("|%-11s | %-11s | %-11s|",
                                   c.getStudentId(), c.getStudentName(), c.getSemester_id()));
                       }
                   }
                    break;

                case 4:
                    System.out.println("Exit");
                    System.out.println("**********************SESSION LOGGED OUT*****************************");
                    System.exit(0);

                default:
                    System.out.println("No task for the day");
            }
        }
    }
}
