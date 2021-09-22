
package com.lt.client;

import com.lt.bean.Roles;
import com.lt.bean.Student;
import com.lt.business.ProfessorImplService;
import com.lt.business.StudentImplService;
import com.lt.business.UserImplServiceInterface;
import com.lt.exception.CourseNotAssignedToProfessorException;
import com.lt.exception.CourseNotFoundException;
import com.lt.exception.GradeNotAddedException;
import com.lt.exception.ProfessorNotFoundException;
import com.lt.exception.RoleNotFoundException;
import com.lt.exception.StudentNotFoundException;
import com.lt.exception.UserNotFoundException;
import com.lt.exception.StudentDetailsNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


/**
 * @author CRS Applicaion Menu Class. Its main method class to run the application.
 * Bases on Options all the methods get called.
 */
public class CRSApplication {
    public static void main(String[] args) throws ParseException, SQLException, IOException, UserNotFoundException, CourseNotFoundException, StudentNotFoundException, GradeNotAddedException, ProfessorNotFoundException, CourseNotAssignedToProfessorException, RoleNotFoundException {

        StudentImplService studentImplService = new StudentImplService();
        ProfessorImplService professorImplService = new ProfessorImplService();
        UserImplServiceInterface userImplService = new UserImplServiceInterface();


        System.out.println("Welcome to CRSApplication");
        System.out.println("----------------------------------------------------------------------");

        Boolean permission = true;
        while (permission) {
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            System.out.println("3. Update password");
            System.out.println("4. Exit");
            System.out.println("Choose your Choice");
            Scanner sc = new Scanner(System.in);
            int role = sc.nextInt();
            switch (role) {
                case 1:

                    System.out.println("Login");
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Enter UserName : ");
                    String userName = sc.next();
                    System.out.println("Enter Password : ");
                    String passWord = sc.next();
                    int rol = userImplService.login(userName, passWord);
                    if(rol ==0){
                        System.out.println("User details not found..! Contact admin");
                    }
                    else {
                        userImplService.getUserMenu(rol, userName);
                    }
                    System.out.println("***************************************************");
                    break;

                case 2:
                    System.out.println("Sign up");
                    System.out.println("-----------------------------------------------");
                    System.out.println("Enter your Id: ");
                    Long Id = sc.nextLong();
                    System.out.println("Enter your Name : ");
                    String Name = sc.next();
                    System.out.println("Enter your Email: ");
                    String Email = sc.next();
                    System.out.println("Enter your Gender: ");
                    String Gender = sc.next();
                    char Gen = Gender.charAt(0);
                    System.out.println("Enter your DOB: ");
                    String Dobs = sc.next();
                    Date Dob = new SimpleDateFormat("dd/MM/yyyy").parse(Dobs);
                    System.out.println("Enter your Contact No: ");
                    Long Contact = sc.nextLong();
                    System.out.println("Enter Semester id: ");
                    Long Semester = sc.nextLong();
                    System.out.println("Enter your New PassWord: ");
                    String stdPassword = sc.next();
                    Student student = new Student(Id, Name, Email, Gen, Dob, Contact, Semester, stdPassword);
                    boolean flagStudentSignUp = studentImplService.signUp(student);
                    if (flagStudentSignUp) {
                        System.out.println("SignUp SuccessFul");
                    } else {
                        System.out.println("Approval is Pending from Admin");
                    }
                    System.out.println("***************************************************");
                    break;
                case 3:
                    System.out.println("Update Password");
                    System.out.println("-----------------------------------------------");
                    System.out.println("Enter Old Password");
                    String oldPassword = sc.nextLine();
                    System.out.println("Enter New Password");
                    String newPassword = sc.nextLine();
                    System.out.println("Enter Confirm Password");
                    String confPassword = sc.nextLine();
                    if (newPassword.equals(confPassword)) {
                        System.out.println("Password changed Successfully");
                    } else {
                        System.out.println("password do not Match");
                    }
                    System.out.println("***************************************************");
                    break;

                case 4:
                    System.out.println("Exit");
                    System.out.println("**************Your Session is Logged out*******************");
                    break;

                default:
                    System.out.println("Invalid Choice");
                    System.out.println("***************************************************");
            }
        }
    }
}

