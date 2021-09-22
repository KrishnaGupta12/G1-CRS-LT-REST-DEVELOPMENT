package com.lt.bean;

public class Grade {
    private long courseId;
    private String courseName;
    private long studentId;
    private long semesterId;
    private String grade;

    public Grade() {
    }

    public Grade(long courseId,String courseName, long studentId,long semesterId, String grade) {
        this.courseId = courseId;
        this.courseName= courseName;
        this.studentId = studentId;
        this.semesterId= semesterId;
        this.grade = grade;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(long semesterId) {
        this.semesterId = semesterId;
    }
}
