package com.lt.bean;

public class Courses {
    private long courseId;
    private String courseName;
    private double courseFee;
    private String courseDuration;
    private String courseType;
    private String courseDetails;
    private long courseSemesterId;
    private long professorId;

    public Courses() {
    }

    public Courses(long courseId, String courseName){
        this.courseId = courseId;
        this.courseName = courseName;

    }

    public Courses(String courseName, double courseFee, String courseDuration, String courseType, String courseDetails, long courseSemesterId, long professorId) {
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.courseDuration = courseDuration;
        this.courseType = courseType;
        this.courseDetails = courseDetails;
        this.courseSemesterId = courseSemesterId;
        this.professorId = professorId;
    }

    public Courses(long courseId, String courseName, double courseFee, String courseDuration, String courseType, String courseDetails) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.courseDuration = courseDuration;
        this.courseType = courseType;
        this.courseDetails = courseDetails;
    }

    public Courses(long courseId, String courseName, double courseFee, String courseDuration,
                   String courseType, String courseDetails, long courseSemesterId, long professorId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.courseDuration = courseDuration;
        this.courseType = courseType;
        this.courseDetails = courseDetails;
        this.courseSemesterId = courseSemesterId;
        this.professorId = professorId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(String courseDetails) {
        this.courseDetails = courseDetails;
    }

    public long getCourseSemesterId() {
        return courseSemesterId;
    }

    public void setCourseSemesterId(long courseSemesterId) {
        this.courseSemesterId = courseSemesterId;
    }

    public long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    @Override
    public String toString() {
        return "\nCourse{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseFee=" + courseFee +
                ", courseDuration='" + courseDuration + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseDetails='" + courseDetails + '\'' +
                ", courseSemesterId=" + courseSemesterId +
                ", professorId=" + professorId +
                '}';
    }

}
