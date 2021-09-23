package com.lt.dao;

import com.lt.bean.*;
import com.lt.constants.SqlConstants;
import com.lt.exception.*;
import com.lt.util.DBUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * @author Implementation of Student DAO Interface all methods to interacts with DB
 */
@Component
public class StudentDaoImpl implements StudentDaoInterface {
    private static Logger logger = Logger.getLogger(StudentDaoImpl.class);

    private static volatile StudentDaoImpl instance = null;

    StudentDaoImpl() {
    }

    public static StudentDaoImpl getInstance() {
        if (instance == null) {
            synchronized (StudentDaoImpl.class) {
                instance = new StudentDaoImpl();
            }
        }
        return instance;
    }

    Connection con = DBUtil.getConnection();
    PreparedStatement smt = null;
    static int count = 10001;


    /**
     * Student signup method
     */
    @Override
    public boolean signUp(Student student) throws SQLException {
        smt = con.prepareStatement(SqlConstants.INSERT_TO_STUDENT);
        smt.setInt(1, (int) student.getStudentId());
        smt.setString(2, student.getStudentName());
        smt.setString(3, student.getStudentEmail());
        smt.setString(4, String.valueOf(student.getGender()));
        smt.setDate(5, new Date(student.getStudentDOB().getTime()));
        smt.setInt(6, (int) student.getContact_no());
        smt.setInt(7, (int) student.getSemester_id());
        smt.setString(8, student.getPassWord());
        smt.setInt(9, 0);
        int flag = smt.executeUpdate();
        if (flag != 0)
            return true;
        return false;
    }

    /**
     * Student Register for Courses Method Implementation
     */
    @Override
    public boolean registerForCourse(long student_id, long semesterId, long courseId) throws SQLException {
        int flag = 0;

        try {
            Set<RegisterCourse> registeredCourses = viewRegisteredCourses(student_id, semesterId);
            if (isRegisteredCourse(registeredCourses, courseId, student_id)) {
                throw new CourseAlreadyRegisteredException();
            }
            smt = con.prepareStatement(SqlConstants.REGISTER_COURSE);
            smt.setInt(1, (int) student_id);
            smt.setInt(2, (int) semesterId);
            smt.setInt(3, (int) courseId);
            flag = smt.executeUpdate();
            if (flag != 0)
                return true;
        } catch (CourseAlreadyRegisteredException e) {
            logger.error(e.getMessage());
        }

        return false;
    }

    /**
     * Show list of registered courses by student
     */
    @Override
    public Set<RegisterCourse> viewRegisteredCourses(long studentId, long semesterId) throws SQLException {
        Set<RegisterCourse> registeredList = new HashSet<RegisterCourse>();
        try {
            smt = con.prepareStatement(SqlConstants.LIST_REGISTERED_COURSES);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                long course_id = rs.getInt(1);
                String course_name = rs.getString(2);
                double course_fees = rs.getDouble(3);
                String course_duration = rs.getString(4);
                String course_type = rs.getString(5);
                String course_details = rs.getString(6);
                String paymentStatus = rs.getString(7);
                long course_semester_id = rs.getInt(8);
                long student_id = rs.getInt(9);
                RegisterCourse neWCourse = new RegisterCourse(course_id, course_name, course_fees, course_duration, course_details, course_type, paymentStatus);
                if (course_semester_id == semesterId && student_id == studentId)
                    registeredList.add(neWCourse);
            }
            if (registeredList.isEmpty()) {
                throw new CourseDetailsNotFoundException();
            }
        } catch (CourseDetailsNotFoundException e) {
          logger.error(e.getMsg());
           // logger.error(e.getMessage());
        }

        return registeredList;
    }


    /**
     * Student removing courses method implementation
     */
    @Override
    public boolean removeCourse(long courseId) throws SQLException {
        smt = con.prepareStatement(SqlConstants.REMOVE_COURSE);
        smt.setInt(1, (int) courseId);
        int flag = smt.executeUpdate();
        if (flag != 0)
            return true;
        return false;
    }


    /**
     * Show list of student
     */
    @Override
    public Student getStudent(String username) throws SQLException {
        Student student = null;
        long stud_id = 0L;
        smt = con.prepareStatement(SqlConstants.GET_STUDENT_DATA);
        smt.setString(1, username);
        ResultSet rs = smt.executeQuery();
        while (rs.next()) {
            stud_id = (long) rs.getInt(1);
            String stude_name = rs.getString(2);
            student = new Student(stud_id, stude_name);
        }
        try {
            if(student == null)
                throw new StudentDetailsNotFoundException();
        } catch (StudentDetailsNotFoundException e) {
            logger.error(e.getMsg());
        }
        return student;
    }


    /**
     * Show available courses method
     */
    @Override
    public List<Courses> showAvailableCourses(long semesterId) throws SQLException {
        List<Courses> availableList = new ArrayList<Courses>();
        try {
            smt = con.prepareStatement(SqlConstants.AVAILABLE_COURSES);
            smt.setInt(1, (int) semesterId);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                long course_id = rs.getInt(1);
                String course_name = rs.getString(2);
                double course_fees = rs.getDouble(3);
                String course_duration = rs.getString(4);
                String course_type = rs.getString(5);
                String course_details = rs.getString(6);
                Courses neWCourse = new Courses(course_id, course_name, course_fees, course_duration, course_type, course_details);
                availableList.add(neWCourse);
            }
            if (availableList.isEmpty()) {
                throw new CourseDetailsNotFoundException();
            }
        } catch (CourseDetailsNotFoundException e) {
            logger.error(e.getMsg());
        }
        return availableList;
    }

    /**
     * Show List of Courses with Pending payment status
     */
    @Override
    public Set<RegisterCourse> showListofPendingPayment(long student_id) throws SQLException {
        Set<RegisterCourse> list = new HashSet<RegisterCourse>();
        smt = con.prepareStatement(SqlConstants.PENDING_PAYMENT_LIST);
        ResultSet rs = smt.executeQuery();
        while (rs.next()) {
            long course_id = rs.getInt(1);
            String course_name = rs.getString(2);
            double course_fees = rs.getDouble(3);
            String course_duration = rs.getString(4);
            String course_type = rs.getString(5);
            String course_details = rs.getString(6);
            String paymentStatus = rs.getString(7);
            long studentid = rs.getInt(8);
            RegisterCourse neWCourse = new RegisterCourse(course_id, course_name, course_fees, course_duration, course_details, course_type, paymentStatus);
            if (neWCourse.getPaymentStatus().equalsIgnoreCase("pending") && studentid == student_id)
                list.add(neWCourse);
        }
        return list;
    }

    /**
     * Registered Course Payfees Method
     */
    @Override
    public boolean payfees(long courseId, Payment payment, long studentId) throws SQLException {

        smt = con.prepareStatement(SqlConstants.INSERT_PAYMENT_STATUS);
        smt.setString(1, payment.getBillingMode());
        smt.setDouble(2, payment.getBillingAmount());
        smt.setInt(3, (int) courseId);
        smt.setInt(4, (int) studentId);
        smt.setInt(5, (int) payment.getTransactionId());

        if (smt.executeUpdate() != 0) {
            smt = con.prepareStatement(SqlConstants.UPDATE_PAYMENT_STATUS);
            smt.setInt(1, (int) courseId);
            smt.setInt(2, (int) studentId);
            int flag = smt.executeUpdate();
            System.out.println("Billing details updated");
            return true;
        }
        return false;
    }

    @Override
    public List<GradeCard> viewGradeCard(long semesterId, long studentId) throws SQLException {
        List<GradeCard> gradeCardLits = new ArrayList<GradeCard>();
        smt = con.prepareStatement(SqlConstants.VIEW_GRADE_CARD_STUDENT);
        smt.setLong(1, studentId);
        smt.setLong(2, semesterId);
        ResultSet rs = smt.executeQuery();
        while (rs.next()) {
            long stud_id = rs.getInt(1);
            String stud_name = rs.getString(2);
            long course_id = rs.getInt(3);
            String course_name = rs.getString(4);
            long sem_id = rs.getInt(5);
            String grade_name = rs.getString(6);
            GradeCard grade = new GradeCard(stud_id, stud_name, course_id, course_name, sem_id, grade_name);
            gradeCardLits.add(grade);
        }
        return gradeCardLits;
    }

    @Override
    public boolean payfeesCard(long courseId, Payment payment, long studentId) throws SQLException {
        smt = con.prepareStatement(SqlConstants.INSERT_PAYMENT_STATUS_VIA_CARD);
        smt.setString(1, payment.getBillingMode());
        smt.setDouble(2, payment.getBillingAmount());
        smt.setString(3, payment.getCard_no());
        smt.setString(4, payment.getCard_expiry());
        smt.setInt(5, (int) courseId);
        smt.setInt(6, (int) studentId);
        smt.setInt(7, (int) payment.getTransactionId());

        if (smt.executeUpdate() != 0) {
            smt = con.prepareStatement(SqlConstants.UPDATE_PAYMENT_STATUS);
            smt.setInt(1, (int) courseId);
            smt.setInt(2, (int) studentId);
            int flag = smt.executeUpdate();
            System.out.println("Billing details updated");
            return true;
        }
        return false;
    }


    public static boolean isRegisteredCourse(Set<RegisterCourse> viewRegisteredCourses, long courseId, long studentId) {
        for (RegisterCourse course : viewRegisteredCourses) {
            if (courseId == course.getCourseId()) {
                return true;
            }
        }
        return false;
    }


}







