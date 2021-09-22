package com.lt.client;

import com.lt.bean.Courses;
import com.lt.bean.GradeCard;
import com.lt.bean.Payment;
import com.lt.bean.RegisterCourse;
import com.lt.business.StudentImplService;
import com.lt.constants.ModeOfPayment;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;


/**
 * @author Student Individual Menu with all the Student functionality
 */
public class StudentMenu {
    public void studentSession(String userName, long student_id, String studentName, String loginTime) throws SQLException {
        System.out.println("Welcome " + studentName + " to your panel. Have a Good day!!");
        System.out.println("Login at : " + loginTime);
        System.out.println("---------------------------------------------------------------");
        StudentImplService studentImplService = new StudentImplService();

        Boolean permission = true;
        while (permission) {
            System.out.println("Choose your Tasks");
            System.out.println("1. Register Course");
            System.out.println("2. Remove course");
            System.out.println("3. View Course");
            System.out.println("4. Pay Fees");
            System.out.println("5. View Grade Report");
            System.out.println("6. Logout");
            Scanner sc = new Scanner(System.in);
            int task = sc.nextInt();
            switch (task) {
                case 1:
                    System.out.println("Register Course");
                    System.out.println("-----------------------------------------------");
                    System.out.println("Enter Semester id");
                    long semester_id = sc.nextLong();
                    System.out.println("Choose a Course from the Below List available for selected semester : ");
                    List<Courses> availableList = studentImplService.showAvailableCourses(semester_id);
                    if (!availableList.isEmpty()) {
                        System.out.println(String.format("|%-10s | %-10s | %-10s| %-9s|", "-----------", "-----------", "---------", "-------"));
                        System.out.println(String.format("|%-10s | %-10s | %-10s| %-11s|", "COURSE ID", "COURSE NAME", "DETAILS", "FEES"));
                        System.out.println(String.format("|%-10s | %-10s | %-10s| %-9s|", "-----------", "-----------", "---------", "-------"));
                        availableList.stream().forEach(c -> System.out.println(String.format("|%-11s | %-11s | %-11s| %-10s| ",
                                c.getCourseId(), c.getCourseName(), c.getCourseDetails(), c.getCourseFee())));
                        System.out.println("Enter Course Id of course you want to register: ");
                        long courseId = sc.nextLong();
                        boolean flag = studentImplService.registerForCourse(student_id, semester_id, courseId);
                        if (flag) {
                            System.out.println("Course Registered ..! Payment Status is pending. Please Pay your fees");
                        } else
                            System.out.println("Sorry!! Seats are Full!! Try Different Course..");
                    }
                    System.out.println("***************************************************");
                    break;
                case 2:
                    System.out.println("Remove course");
                    System.out.println("-----------------------------------------------");
                    System.out.println("Enter Semester id");
                    long s_id = sc.nextLong();
                    System.out.println("Your Registered Courses are : \n");
                    Set<RegisterCourse> list = studentImplService.viewRegisteredCourses(student_id, s_id);
                    if (list.isEmpty()) {
                        System.out.println("No courses registered...");
                    } else {
                        System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|", "-----------", "-----------", "---------", "-------"));
                        System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|", "COURSE ID", "COURSE NAME", "DETAILS", "FEES"));
                        System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|", "-----------", "-----------", "---------", "-------"));
                        list.stream().forEach(c -> System.out.println(String.format("|%-11s | %-11s | %-11s| %-11s ",
                                c.getCourseId(), c.getCourseName(), c.getCourseDuration(), c.getCourseFee())));
                        System.out.println("Enter Course Id of course you want to Delete: ");
                        Long deleteCourseId = sc.nextLong();
                        if (studentImplService.checkId(deleteCourseId, list)) {
                            boolean status = studentImplService.removeCourse(deleteCourseId);
                            if (status)
                                System.out.println("Course deleted SuccessFully..!");
                        } else
                            System.out.println("Invalid CourseId...");

                    }
                    System.out.println("***************************************************");
                    break;
                case 3:
                    System.out.println("Enter Semester id");
                    long sem_id = sc.nextLong();
                    System.out.println("View Registered Courses");
                    Set<RegisterCourse> registeredCourses = studentImplService.viewRegisteredCourses(student_id, sem_id);
                    //System.out.println(registeredCourses);
                    System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s| %-10s|", "-----------", "-----------", "-----------", "-----------", "----------------"));
                    System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s| %-10s|", "COURSE ID", "COURSE NAME", "DETAILS", "FEES", "PAYMENT STATUS"));
                    System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s| %-10s|", "-----------", "-----------", "-----------", "-----------", "----------------"));

                    registeredCourses.stream().forEach(c -> System.out.println(String.format("|%-11s | %-11s | %-11s| %-11s| %-11s| ",
                            c.getCourseId(), c.getCourseName(), c.getCourseDuration(), c.getCourseFee(), c.getPaymentStatus())));
                    System.out.println("*******************************************************************");
                    break;
                case 4:
                    System.out.println("Pay Fees");
                    Set<RegisterCourse> pendingPaymentList = studentImplService.showListofPendingPayment(student_id);
                    //System.out.println(pendingPaymentList);
                    System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s| %-10s|", "-----------", "-----------", "--------------", "-------", "----------------"));
                    System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s| %-10s|", "COURSE ID", "COURSE NAME", "DETAILS", "FEES", "PAYMENT STATUS"));
                    System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s| %-10s|", "-----------", "-----------", "--------------", "-------", "----------------"));

                    pendingPaymentList.forEach(c -> System.out.println(String.format("|%-11s | %-11s | %-11s| %-11s| %-11s| ",
                            c.getCourseId(), c.getCourseName(), c.getCourseDuration(), c.getCourseFee(), c.getPaymentStatus())));

                    if (pendingPaymentList.size() == 0) {
                        System.out.println("No courses pending for payment");
                    } else {
                        System.out.println("Do you want to pay");
                        System.out.println("Enter yes/no : ");
                        String choice = sc.next();
                        if (choice.equalsIgnoreCase("yes")) {
                            System.out.println("Enter Course id");
                            long course_Id = Long.parseLong(sc.next());
                            System.out.println("Select Mode of Payment");
                            System.out.println("1. CASH");
                            System.out.println("2. CREDIT_CARD");
                            System.out.println("3. DEBIT_CARD");
                            int mode = sc.nextInt();
                            ModeOfPayment modePayment = ModeOfPayment.getModeofPayment(mode);
                            boolean paymentFlag = false;
                            if (modePayment.toString().equalsIgnoreCase("CASH")) {
                                System.out.println("Enter Amount to pay");
                                double amount = Double.parseDouble(sc.next());
                                long transactionId = Long.parseLong(studentImplService.generateTransactionId());
                                Payment payment = new Payment(amount, modePayment.toString(), transactionId);
                                paymentFlag = studentImplService.payfees(course_Id, payment, student_id);

                            } else if (modePayment.toString().equalsIgnoreCase("CREDIT_CARD") || modePayment.toString().equalsIgnoreCase("DEBIT_CARD")) {
                                System.out.println("Enter Credit/Debit Card Number");
                                String cardNo = sc.next();
                                if (!StudentImplService.validateCard(cardNo)) {
                                    System.out.println("Card is not Valid");
                                } else {
                                    System.out.println("Enter Expiry of Card(MM/YYYY)");
                                    String expiry = sc.next();
                                    System.out.println("Enter Amount to pay");
                                    double amount = Double.parseDouble(sc.next());
                                    long transactionId = Long.parseLong(studentImplService.generateTransactionId());
                                    Payment payment = new Payment(transactionId, amount, modePayment.toString(), cardNo, expiry);
                                    paymentFlag = studentImplService.payfeesCard(course_Id, payment, student_id);
                                }

                            }
                            if (paymentFlag) {
                                System.out.println("Payment Successful..!");
                            } else {
                                System.out.println("Payment failed..!");
                            }

                        } else {
                            System.out.println("Note : Pay fees to get your seat confirmed ..!!");
                        }

                    }
                    System.out.println("***************************************************");
                    break;
                case 5:
                    System.out.println("View Report card");
                    System.out.println("Enter Semester id");
                    long semester_Id = Long.parseLong(sc.next());
                    List<GradeCard> viewGradeCard = studentImplService.viewGradeCard(semester_Id, student_id);
                    //System.out.println(viewGradeCard);
                    System.out.println("Your Report Card for semester " + semester_Id + ":-");
                    System.out.println(String.format("|%-10s | %-10s | %-10s|", "-----------", "-----------", "--------------"));
                    System.out.println(String.format("|%-10s | %-10s | %-10s|", "COURSE CODE", "COURSE CODE", "GRADE "));
                    System.out.println(String.format("|%-10s | %-10s | %-10s|", "-----------", "-----------", "--------------"));
                    if (viewGradeCard.size() == 0) {
                        System.out.println("Results are not out.. check with admin ");
                    } else {
                        for (GradeCard g : viewGradeCard) {
                            System.out.println(String.format("|%-11s | %-11s | %-11s|",
                                    g.getCourseId(), g.getCourseName(), g.getGrade()));
                        }
                    }
                    System.out.println("***************************************************");
                    break;
                case 6:
                    System.out.println("Exit");
                    System.out.println("**********************SESSION LOGGED OUT*****************************");
                    System.exit(0);
                default:
                    System.out.println("No task for the day");

            }
        }
    }
}