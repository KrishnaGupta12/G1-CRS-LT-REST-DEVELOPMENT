package com.lt.dao;

import com.lt.bean.Courses;
import com.lt.bean.GradeCard;
import com.lt.bean.Professor;
import com.lt.bean.Student;
import com.lt.constants.SqlConstants;
import com.lt.exception.CourseExistedException;
import com.lt.exception.CourseDetailsNotFoundException;
import com.lt.exception.StudentDetailsNotFoundException;
import com.lt.util.DBUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminDaoImpl implements AdminDaoInterface {

    private final static Logger logger = Logger.getLogger(AdminDaoImpl.class);
    private static volatile AdminDaoImpl instance = null;

    private AdminDaoImpl() {
    }

    public static AdminDaoImpl getInstance() {
        if (instance == null) {
            synchronized (AdminDaoImpl.class) {
                instance = new AdminDaoImpl();
            }
        }
        return instance;
    }

    Connection con = DBUtil.getConnection();
    PreparedStatement statement = null;

    @Override
    public boolean addProfessor(Professor professor) throws SQLException {
        try {

            // long id = professor.getProfessorId();
            String name = professor.getProfessorName();
            String email = professor.getProfessorEmail();
            long courseid = professor.getCourseId();
            String department = professor.getProfessorDPTName();
            String password = professor.getPassword();

            statement = con.prepareStatement(SqlConstants.ADD_PROFESSOR);
            //  statement.setLong(1, id);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setLong(3, courseid);
            statement.setString(4, department);
            statement.setString(5, password);

            int status = statement.executeUpdate();
            if (status != 0) {
                statement = con.prepareStatement(SqlConstants.INSERT_USER);
                statement.setString(1, email);
                statement.setString(2, password);
                statement.setInt(3, 2);
                if (statement.executeUpdate() != 0) {
                    System.out.println("Professor added successfully..!");
                    return true;
                } else {
                    System.out.println("Data not added..!");
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
		return false;

    }

    @Override
    public boolean approveStudent(int studentId) throws SQLException, StudentDetailsNotFoundException {

        try {
            List<Student> list = showListOfPendingStudent();

            {
                statement = con.prepareStatement(SqlConstants.APPROVE_STUDENT);

                statement.setInt(1, 1);
                statement.setInt(2, studentId);
                int apprv_status = statement.executeUpdate();
                if (apprv_status == 0)
                    System.out.println("Approval unsuccessful....");
                else {
                    statement = con.prepareStatement(SqlConstants.INSERT_USER);
                    for (Student student : list) {
                        if (student.getStudentId() == studentId) {
                            statement.setString(1, student.getStudentEmail());
                            statement.setString(2, student.getPassWord());
                            statement.setInt(3, 1);

                            int flag = statement.executeUpdate();
                            if (flag != 0) {

                                System.out.println("Student is approved ....");

                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());

        }
		return false;
    }

    @Override
    public List<Student> showListOfPendingStudent() throws SQLException {
        List<Student> pendingList = new ArrayList<Student>();

      //  try {

            statement = con.prepareStatement(SqlConstants.UNAPPROVE_STUDENT);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long studId = rs.getInt(1);
                String studName = rs.getString(2);
                String studEmail = rs.getString(3);
                String studPassword = rs.getString(4);
                Student std = new Student();
                std.setStudentId(studId);
                std.setStudentName(studName);
                std.setStudentEmail(studEmail);
                std.setPassWord(studPassword);
                
                Student std1 = new Student(studId,studName,studEmail,studPassword);
                pendingList.add(std1);
            }
//            if (pendingList.isEmpty()) {
//                throw new StudentDetailsNotFoundException();
//            }
//        } catch (StudentDetailsNotFoundException e) {
//            logger.error(e.getMsg());
//        }
        return pendingList;
    }


    @Override
    public boolean generateReportCard() throws SQLException {
        statement = con.prepareStatement(SqlConstants.GENERATE_REPORT_CARD);
        List<GradeCard> studentList = new ArrayList<GradeCard>();
        ResultSet rs = statement.executeQuery();
        int flag = 0;
        while (rs.next()) {
            long studId = rs.getInt(1);
            String studName = rs.getString(2);
            long courseId = rs.getInt(3);
            String courseName = rs.getString(4);
            long semesterId = rs.getInt(5);
            String grade = rs.getString(6);
            GradeCard gradeCard = new GradeCard(studId, studName, courseId, courseName, semesterId, grade);
            studentList.add(gradeCard);
        }
//        System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","-----------","-----------","---------" ,"-------")) ;
//        System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","STUDENT ID","STUDENT NAME","DETAILS","FEES"));
//        System.out.println(String.format("|%-10s | %-10s | %-10s| %-10s|","-----------","-----------","---------" ,"-------"));
        // studentList.stream().forEach(grade -> System.out.println(grade) );
        for (GradeCard grade : studentList) {
            statement = con.prepareStatement(SqlConstants.INSERT_GRADE_CARD);
            statement.setLong(1, grade.getStudentId());
            statement.setString(2, grade.getStudentName());
            statement.setLong(3, grade.getCourseId());
            statement.setString(4, grade.getCourseName());
            statement.setLong(5, grade.getSemesterId());
            statement.setString(6, grade.getGrade());
            flag = statement.executeUpdate();
        }
        if (flag != 0) {
            System.out.println("Report card generated");
        return true;    
        }
		return false;

    }


    @Override
    public boolean addCourse(Courses course) throws SQLException, CourseExistedException {
        try {
            List<Courses> courseList = adminViewAllCourses();
            if(checkId(course.getCourseId(),courseList)){
                throw new CourseExistedException();
            }
            statement = con.prepareStatement(SqlConstants.ADD_COURSES);
            statement.setLong(1, course.getCourseId());
            statement.setString(2, course.getCourseName());
            statement.setDouble(3, course.getCourseFee());
            statement.setString(4, course.getCourseDuration());
            statement.setString(5, course.getCourseType());
            statement.setString(6, course.getCourseDetails());
            statement.setLong(7, course.getCourseSemesterId());
            statement.setLong(8, course.getProfessorId());
            int updateStatus = statement.executeUpdate();
            if (updateStatus!= 0) {
            	statement = con.prepareStatement(SqlConstants.INSERT_COURSE_CATALOG);
                statement.setLong(1, course.getCourseId());
                statement.setString(2, "true");
                System.out.println(" Record successfully updated ...!!");
                return true;
            } 
        
        }
        catch (CourseExistedException e) {
            logger.error(e.getMsg(course.getCourseId()));

        }
        System.out.println(" Record not updated...try again !!");
		return false;


    }

    @Override
    public boolean deleteCourse(long courseId,List<Courses> coursesList) throws SQLException {
        try {
            if (!checkId(courseId, coursesList)) {
                throw new CourseDetailsNotFoundException();
            }

            statement = con.prepareStatement(SqlConstants.DEL_COURSES);
            statement.setInt(1, (int) courseId);
            int delStatus = statement.executeUpdate();
            if (delStatus == 0)
                System.out.println(" Invalid CourseId !!....");
            else {
                statement = con.prepareStatement(SqlConstants.DEL_COURSES_CATALOG);
                statement.setInt(1, (int) courseId);
                int del = statement.executeUpdate();
                if (del != 0)
                    System.out.println("Course  deleted successfully : ");
            }

        } catch (CourseDetailsNotFoundException e) {
            logger.error(e.getMsg());

        }
		return false;
    }

    public boolean checkId(long id,List<Courses> list ){
        for (Courses c :list) {
            if(c.getCourseId() == id)
                return true;
        }
        return false;
    }

    @Override
    public List<Courses> adminViewAllCourses() throws SQLException {

        List<Courses> courses = new ArrayList<Courses>();
        statement = con.prepareStatement(SqlConstants.VIEW_ALL_COURSES);
        ResultSet resultSet = statement.executeQuery();
        try {
            while (resultSet.next()) {
                Courses course = new Courses();

                course.setCourseId(resultSet.getLong(1));
                course.setCourseName(resultSet.getString(2));
                course.setCourseFee(resultSet.getDouble(3));
                course.setCourseDuration(resultSet.getString(4));
                course.setCourseType(resultSet.getString(5));
                course.setCourseDetails(resultSet.getString(6));
                course.setCourseSemesterId(resultSet.getLong(7));
                course.setProfessorId(resultSet.getLong(8));
                courses.add(course);

            }
            if(courses.isEmpty()){
                throw new CourseDetailsNotFoundException();
            }
        } catch (CourseDetailsNotFoundException ex) {
            logger.error(ex.getMsg());
        }


        return courses;
    }

}
