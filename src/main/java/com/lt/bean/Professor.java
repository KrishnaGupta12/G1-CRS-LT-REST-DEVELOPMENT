package com.lt.bean;

public class Professor {

    private long professorId;
    private String professorName;
    private String professorEmail;
    private long courseId;
    private String professorDPTName;
    private String password;

    public Professor() {
    }

    public Professor(long professorId, String professorName, String professorEmail, long courseId, String professorDPTName, String password) {
        this.professorId = professorId;
        this.professorName = professorName;
        this.professorEmail = professorEmail;
        this.courseId = courseId;
        this.professorDPTName = professorDPTName;
        this.password = password;
    }

    public Professor(String professorName, String professorEmail, long courseId, String professorDPTName, String password) {
        this.professorId = professorId;
        this.professorName = professorName;
        this.professorEmail = professorEmail;
        this.courseId = courseId;
        this.professorDPTName = professorDPTName;
        this.password = password;
    }

    public Professor(long prof_id, String prof_name) {
        this.professorId =prof_id;
        this.professorName=prof_name;
    }

    public long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public void setProfessorEmail(String professorEmail) {
        this.professorEmail = professorEmail;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfessorDPTName() {
        return professorDPTName;
    }

    public void setProfessorDPTName(String professorDPTName) {
        this.professorDPTName = professorDPTName;
    }

}
