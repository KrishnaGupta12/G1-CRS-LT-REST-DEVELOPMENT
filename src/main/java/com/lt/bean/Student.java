package com.lt.bean;

import java.util.Date;
import java.util.List;

public class Student extends User {
    private long studentId;
    private String studentName;
    private String studentEmail;
    private char gender;
    private Date studentDOB;
    private long contact_no;
    private long semester_id;
    private String passWord;
    private List<Courses> listCourses;

    public Student() {
    }

    public Student(long studentId, String studentName, String studentEmail, char gender, Date studentDOB,
                   long contact_no, long semester_id, String passWord) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.gender = gender;
        this.studentDOB = studentDOB;
        this.contact_no = contact_no;
        this.semester_id = semester_id;
        this.passWord = passWord;
    }

    public Student(long stud_id, String stude_name) {
        this.studentId =stud_id;
        this.studentName = stude_name;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getStudentDOB() {
        return studentDOB;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public long getContact_no() {
        return contact_no;
    }

    public void setContact_no(long contact_no) {
        this.contact_no = contact_no;
    }

    public long getSemester_id() {
        return semester_id;
    }

    public void setSemester_id(long semester_id) {
        this.semester_id = semester_id;
    }
     /*public List<Courses> getListCourses() {
        return listCourses;
    }*/

    /*public void setListCourses(List<Courses> listCourses) {
        this.listCourses = listCourses;
    }
*/
    public void setStudentDOB(Date studentDOB) {
        this.studentDOB = studentDOB;
    }

    @Override
    public String toString() {
        return "\nStudent{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", gender=" + gender +
                ", studentDOB=" + studentDOB +
                ", contact_no=" + contact_no +
                '}';
    }
}
