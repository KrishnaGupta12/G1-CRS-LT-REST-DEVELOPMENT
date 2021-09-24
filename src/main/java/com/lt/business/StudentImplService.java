package com.lt.business;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lt.bean.Courses;
import com.lt.bean.GradeCard;
import com.lt.bean.Payment;
import com.lt.bean.RegisterCourse;
import com.lt.bean.Student;
import com.lt.dao.StudentDaoImpl;
import com.lt.dao.StudentDaoInterface;

/**
 * @author Student Business Layer implementing student DAO.
 */
@Component
public class StudentImplService implements StudentDaoInterface {
	@Autowired
	StudentDaoImpl studentDao;
  
    boolean flag = false;


    /**
     * this method will do Student Sign up
     */
    @Override
    public boolean signUp(Student student) throws SQLException {
        //studentDao = new StudentDaoImpl();
        flag = studentDao.signUp(student);
        return flag;

    }


    /**
     * register for course
     */
    @Override
    public boolean registerForCourse(long student_id, long semesterId, long courseId) throws SQLException {
        //  studentDao = new StudentDaoImpl();
        flag = studentDao.registerForCourse(student_id, semesterId, courseId);
        return flag;
    }


    /**
     * show list of registered courses by student
     */
    @Override
    public Set<RegisterCourse> viewRegisteredCourses(long studentId, long semesterId) throws SQLException {
        // studentDao = new StudentDaoImpl();
        Set<RegisterCourse> registeredList = studentDao.viewRegisteredCourses(studentId, semesterId);
        return registeredList;
    }

    /**
     * remove registered courses method
     */
    @Override
    public boolean removeCourse(long courseId) throws SQLException {
        return studentDao.removeCourse(courseId);
    }

    /**
     * get student ID method
     */
    @Override
    public Student getStudent(String username) throws SQLException {
        // studentDao = new StudentDaoImpl();
        Student st = studentDao.getStudent(username);
        return st;
    }

    /**
     * show list of available courses
     */
    @Override
    public List<Courses> showAvailableCourses(long semesterId) throws SQLException {
        List<Courses> list = null;
        // studentDao = new StudentDaoImpl();
        list = studentDao.showAvailableCourses(semesterId);
        return list;
    }

    /**
     * show list of registered courses with pending payment status
     */
    @Override
    public Set<RegisterCourse> showListofPendingPayment(long student_id) throws SQLException {
        Set<RegisterCourse> list = new HashSet<RegisterCourse>();
        //  studentDao = new StudentDaoImpl();
        list = studentDao.showListofPendingPayment(student_id);
        return list;
    }

    /**
     * payfees method
     */
    @Override
    public boolean payfees(long courseId, Payment payment, long studentId) throws SQLException {
        //  studentDao = new StudentDaoImpl();
        return studentDao.payfees(courseId, payment, studentId);
    }

    @Override
    public List<GradeCard> viewGradeCard(long semesterId, long studentId) throws SQLException {
        //  studentDao = new StudentDaoImpl();
        List<GradeCard> gradecard = studentDao.viewGradeCard(semesterId, studentId);
        return gradecard;
    }

    @Override
    public boolean payfeesCard(long courseId, Payment payment, long studentId) throws SQLException {
        return studentDao.payfeesCard(courseId, payment, studentId);
    }


    public String generateTransactionId() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%05d", number);
    }


    public static boolean validateCard(String card) {
        if (card.length() == 16)
            return true;
        return false;
    }

    public boolean checkId(long id,Set<RegisterCourse> list ){
        for (RegisterCourse c :list) {
            if(c.getCourseId() == id)
                return true;
        }
        return false;
    }

}
