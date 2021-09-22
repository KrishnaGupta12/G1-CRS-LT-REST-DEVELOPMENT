package com.lt.constants;

public interface SqlConstants {

    //User table queries
    public final static String USER_TABLE_DATA = "select role_id from user where username =? && password=?";
    public final static String GET_ROLE = "select role_id,role from role where role_id=?";
    public final static String INSERT_TO_USER = "insert into user values (?,?,?)";

    //Student Table queries
    public final static String INSERT_TO_STUDENT = "insert into Student values (?,?,?,?,?,?,?,?,?)";
    public final static String GET_STUDENT_DATA = "select stud_id,stud_name from Student where stud_email = ?";

    //Professor Table queries
    public final static String VIEW_COURSE = "select * from course where course_professor_id = ?";
    public final static String ADD_GRADES = " insert into grade values (?,?,?,?,?) ";
    public final static String VIEW_REG_STUDENT = "select  * from Student where stud_id IN (select reg_stud_id from RegisterCourses where reg_course_id IN ( select course_id from Professor where professor_id = ?))";
    public final static String GET_PROFESSOR_DATA = "select professor_id,professor_name from Professor where professor_email = ? ";
    public final static String LIST_REG_COURSES_SEM = "select course_id,course_name from Course where course_id IN (Select reg_course_id from RegisterCourses where reg_stud_id = ? and reg_semester_id = ?)";


    //Course Table queries
    public final static String AVAILABLE_COURSES = "select course_id,course_name,course_fee, course_duration,course_type,course_details from Course where course_id IN (Select course_id from CourseCatalog where isAvailable = 'true') and course_semester_id = ?";
    public static String LIST_REGISTERED_COURSES = "select c.course_id,c.course_name,c.course_fee, c.course_duration,c.course_type,c.course_details,r.payment_status,r.reg_semester_Id,r.reg_stud_id from Course c INNER JOIN RegisterCourses r ON c.course_id =r.reg_course_id ";

    //RegisterCourse Table queries
    public final static String REGISTER_COURSE = "insert into RegisterCourses values (?,?,?,'pending')";
    public final static String REMOVE_COURSE = "delete from RegisterCourses where reg_course_id =?";
    //public final static String PENDING_PAYMENT_LIST = "select c.course_id,c.course_name,c.course_fee, c.course_duration,c.course_type,c.course_details,r.payment_status from Course c INNER JOIN RegisterCourses r ON c.course_id IN ( Select reg_course_id from RegisterCourses where payment_status = 'pending' )";
    public final static String PENDING_PAYMENT_LIST = "select c.course_id,c.course_name,c.course_fee, c.course_duration,c.course_type,c.course_details,r.payment_status,r.reg_stud_id from Course c INNER JOIN RegisterCourses r ON c.course_id = r.reg_course_id ";
    public final static String UPDATE_PAYMENT_STATUS = "Update RegisterCourses set payment_status='Success' where reg_course_id = ? and reg_stud_id =?";
    public final static String INSERT_PAYMENT_STATUS = "insert into payment(bill_mode,bill_amount,course_id,stud_id,transaction_id) values (?,?,?,?,?)";
    public final static String INSERT_PAYMENT_STATUS_VIA_CARD= "insert into payment values (?,?,?,?,?,?,?)";
    public final static String GENERATE_REPORT_CARD = "Select s.stud_id,s.stud_name,g.courseId,g.courseName,g.semesterId,g.grade from Student s INNER JOIN Grade g On stud_id  = studentId";
    public final static String INSERT_GRADE_CARD = "insert into GradeCard values (?,?,?,?,?,?)";


    //Admin Table queries
    public final static String INSERT_TO_ADMIN = "insert into Admin values (?,?,?,?,?,?,?,?)";
    public static final String ADD_PROFESSOR = "insert into professor(professor_name,professor_email,course_id,professor_dept,password) values(?,?,?,?,?)";
   // public static final String ADD_COURSES = "insert into course(course_name,course_fee,course_duration, course_type,course_details,course_semester_id,course_professor_id) values(?,?,?,?,?,?,?)";
    public static final String ADD_COURSES = "insert into course(course_id,course_name,course_fee,course_duration, course_type,course_details,course_semester_id,course_professor_id) values(?,?,?,?,?,?,?,?)";

    public static final String DEL_COURSES = "delete from course where course_id=?";
    public static final String APPROVE_STUDENT = "update student set isApprove = ? where stud_id=?";
    public static final String INSERT_USER = "insert into user values(?,?,?)";
    public static final String UNAPPROVE_STUDENT = "select stud_id,stud_name,stud_email,stud_password from student where isApprove = 0";
    public static final String VIEW_ALL_COURSES = "select * from course";
    public static final String INSERT_COURSE_CATALOG = "insert into coursecatalog values (?,?)";
    public static final String DEL_COURSES_CATALOG = "delete from coursecatalog where course_id=?";


    //GradeCard Table queries

    public static final String VIEW_GRADE_CARD_STUDENT = "select * from GradeCard where stud_id =?  and semester_id =?";


}
