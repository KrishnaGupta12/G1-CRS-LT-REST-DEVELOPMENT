package com.lt.client;

import com.lt.bean.Courses;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.business.AdminImplService;
import com.lt.exception.StudentDetailsNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    public void adminSession(String username, String loginTime) throws SQLException, IOException, StudentDetailsNotFoundException {

        AdminImplService adminImplService = new AdminImplService();
        String userName = username.substring(0,username.indexOf('@'));
        System.out.println("Welcome "+userName+ ". Have a Good day!!");
        System.out.println("Login at : "+loginTime);
        Boolean permission = true;
        while (permission) {
            System.out.println("Choose your Tasks");
            System.out.println("1. Add Professor");
            System.out.println("2. Approve Student");
            System.out.println("3. Add course ");
            System.out.println("4. Delete course");
            System.out.println("5. Generate report cards");
            System.out.println("6. View All Courses");
            System.out.println("7. Logout");
            Scanner sc = new Scanner(System.in);
            int task = sc.nextInt();
            switch (task) {
                case 1:
                    System.out.println(" Insert the details for add Professor ....!!!");
                    System.out.println("-----------------------------------------------");
                    /*System.out.println("Enter the Professor Id: ");
                    Long proId = sc.nextLong();*/
                    System.out.println("Enter the Professor Name : ");
                    String proName = sc.next();
                    System.out.println("Enter the Professor Email: ");
                    String proEmail = sc.next();
                    System.out.println("Enter the Course Id  ");
                    long courseID = sc.nextLong();
                    System.out.println("Enter Professor Department Name");
                    String proDepartmentName = sc.next();
                    System.out.println("Enter the Professor Password : ");
                    String password = sc.next();
                    Professor professor = new Professor(proName, proEmail, courseID, proDepartmentName, password);
                    adminImplService.addProfessor(professor);

                    break;

                case 2:
                    System.out.println("List Of Students waiting for Approval :  ");
                    List<Student> studList = adminImplService.showListOfPendingStudent();

                    if(!studList.isEmpty()) {
                        System.out.println(String.format("|%-10s | %-10s | ", "-----------", "-----------"));
                        System.out.println(String.format("|%-10s | %-10s | ", "STUD ID", "STUD NAME"));
                        System.out.println(String.format("|%-10s | %-10s | ", "-----------", "-----------"));
                        for (Student c : studList) {
                            System.out.println(String.format("|%-11s | %-11s | ",
                                    c.getStudentId(), c.getStudentName()));
                        }
                        System.out.println("Enter the Student id ");
                        int studentid = sc.nextInt();
                        adminImplService.approveStudent(studentid);
                    }
                    break;

                case 3:
                    System.out.println("Create course ");
                    System.out.println(" Insert the Course  Details  ....!!!");
                    System.out.println("------------------------------------------");
                   System.out.println("Enter the Course Id: ");
                    long courseId = (int)sc.nextLong();
                    System.out.println("Enter the Course Name : ");
                    String courseName = sc.next();
                    System.out.println("Enter the Course Fees : ");
                    Double courseFees = Double.parseDouble(sc.next());
                    System.out.println("Enter the CourseDuration : ");
                    String courseDuration = sc.next();
                    System.out.println("Enter the Course Type : ");
                    String courseType = sc.next();
                    System.out.println("Enter the Course Details : ");
                    String courseDetails = sc.next();
                    System.out.println("Enter the Course Semester Id  : ");
                    long courseSemesterId =Long.parseLong(sc.next());
                    System.out.println("Enter the professor Id  :");
                    long professorId = sc.nextLong();

                  //  Courses courses = new Courses(courseName, courseFees, courseDuration, courseType, courseDetails, courseSemesterId, professorId);
                    Courses courses = new Courses(courseId,courseName, courseFees, courseDuration, courseType, courseDetails, courseSemesterId, professorId);

                    adminImplService.addCourse(courses);
                    break;

                case 4:
                    System.out.println(" Delete The Course................");
                    System.out.println(" Choose a course id from below list you want to delete :");
                    List<Courses> coursesList = adminImplService.adminViewAllCourses();
                    System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","-----------","-----------","---------" ,"-------")) ;
                    System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","COURSE ID","COURSE NAME","DETAILS","FEES"));
                    System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","-----------","-----------","---------" ,"-------"));
                    for (Courses c : coursesList ){
                        System.out.println(String.format("|%-11s | %-11s | %-11s| %-11s ",
                                c.getCourseId(),c.getCourseName(),c.getCourseDetails(),c.getCourseFee()));
                    }
                    if(!coursesList.isEmpty()){
                        System.out.println("Enter The courseId");
                        long crsId = sc.nextLong();
                        adminImplService.deleteCourse(crsId,coursesList);
                    }
                    break;

                case 5:
                    System.out.println("Do you want to Generate report card for  students");
                    System.out.println("Enter yes/no : ");
                    String choice = sc.next();
                    if(choice.equalsIgnoreCase("yes"))
                        adminImplService.generateReportCard();
                    else
                        System.out.println("Report Card not generated..");
                    break;

                case 6:
                    System.out.println("View All Courses");
                   List<Courses> course_list = adminImplService.adminViewAllCourses();
                    System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","-----------","-----------","---------" ,"-------")) ;
                    System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","COURSE ID","COURSE NAME","DETAILS","FEES"));
                    System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","-----------","-----------","---------" ,"-------"));
                    for (Courses c : course_list ){
                        System.out.println(String.format("|%-11s | %-11s | %-11s| %-11s ",
                                c.getCourseId(),c.getCourseName(),c.getCourseDetails(),c.getCourseFee()));
                    }
                    break;

                case 7:
                    System.out.println("Exit");
                    System.out.println("**********************SESSION LOGGED OUT*****************************");
                    System.exit(0);
                default:
                    System.out.println("No task for the day");
            }
        }
    }
}
