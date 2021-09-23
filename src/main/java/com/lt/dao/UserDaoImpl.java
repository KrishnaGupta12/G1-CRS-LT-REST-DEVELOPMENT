package com.lt.dao;

import com.lt.bean.Professor;
import com.lt.bean.Roles;
import com.lt.bean.Student;
import com.lt.client.AdminMenu;
import com.lt.client.ProfessorMenu;
import com.lt.client.StudentMenu;
import com.lt.constants.SqlConstants;
import com.lt.exception.*;
import com.lt.util.DBUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * @author User DAO implementation method for login to interacts with DB
 */
@Component
public class UserDaoImpl implements UserDaoInterface {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    Connection con = DBUtil.getConnection();
    PreparedStatement smt = null;
    private static volatile UserDaoImpl instance = null;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                instance = new UserDaoImpl();
            }
        }
        return instance;
    }

    StudentDaoImpl studentDao = StudentDaoImpl.getInstance();
    ProfessorDaoImpl professorDao = ProfessorDaoImpl.getInstance();


    /**
     * User Login Method
     */
    @Override

    public int login(String username, String password) throws SQLException {
        int role = 0;
        try {
            smt = con.prepareStatement(SqlConstants.USER_TABLE_DATA);
            smt.setString(1, username);
            smt.setString(2, password);

            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                role = rs.getInt(1);
            }
            if (role == 0) {
                throw new UserNotFoundException(username, password);
            }
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage(username, password));
        }
        return role;
    }

    @Override
    public Roles getRoleDetails(int roleId) throws SQLException {
        Roles role = null;
        try {
            smt = con.prepareStatement(SqlConstants.GET_ROLE);
            smt.setInt(1, roleId);
            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                int role_id = rs.getInt(1);
                String role_name = rs.getString(2);
                role = new Roles(role_id, role_name);
            }
            if (role == null)
                throw new RoleNotFoundException();
        } catch (SQLException | RoleNotFoundException e) {
            logger.error(e.getMessage());
        }
        return role;
    }

//    @Override
//    public void getUserMenu(int role, String userName) throws SQLException, IOException {
//        try {
//            Roles roles = getRoleDetails(role);
//            if(roles == null){
//                throw new RoleNotFoundException();
//            }
//            if (roles.getRole_id() == role && roles.getRole_name().equalsIgnoreCase("STUDENT")) {
//                StudentMenu studentmenu = new StudentMenu();
//                //  StudentDaoImpl studentDao = new StudentDaoImpl();
//                Student stud = studentDao.getStudent(userName);
//                studentmenu.studentSession(userName, stud.getStudentId(), stud.getStudentName(), getLoginTime());
//            } else if (roles.getRole_id() == role && roles.getRole_name().equalsIgnoreCase("PROFESSOR")) {
//                ProfessorMenu professorMenu = new ProfessorMenu();
//                //   ProfessorDaoImpl professorDao = new ProfessorDaoImpl();
//                Professor professor = professorDao.getProfessorId(userName);
//               // professorMenu.professorSession(userName, professor.getProfessorId(), professor.getProfessorName(), getLoginTime());
//            } else if (roles.getRole_id() == role && roles.getRole_name().equalsIgnoreCase("ADMIN")) {
//                AdminMenu admin = new AdminMenu();
//                admin.adminSession(userName, getLoginTime());
//            } else {
//                System.out.println("Invalid user");
//            }
//            } catch (SQLException | IOException | RoleNotFoundException | ProfessorNotFoundException | CourseNotAssignedToProfessorException | StudentNotFoundException | StudentDetailsNotFoundException e) {
//            logger.error(e.getMessage());
//        }

   // }


    @Override
    public String getLoginTime() {
         LocalDateTime dateTime = LocalDateTime.now();
         String loginTime = dateTime.getDayOfMonth() + " " + dateTime.getMonth() + " " +dateTime.getYear() +" , Time : " +dateTime.getHour()+":"+dateTime.getMinute()+":"+dateTime.getSecond();
        return loginTime;
    }
}
