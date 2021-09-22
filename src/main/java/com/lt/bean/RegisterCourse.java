package com.lt.bean;

import java.util.Objects;

public class RegisterCourse {

    private long courseId;
    private String courseName;
    private double courseFee;
    private String courseType;
    private String courseDuration;
    private String courseDetails;
    private String paymentStatus;

    public RegisterCourse() {
    }

//    public RegisterCourse(long courseId, String courseName, double courseFee, String courseType, String courseDuration, String courseDetails, String paymentStatsu) {
//        super( courseId,  courseName,  courseFee,  courseType,  courseDuration,  courseDetails);
//        this.paymentStatus = paymentStatsu;
//
//    }


    public RegisterCourse(long courseId, String courseName, double courseFee, String courseType, String courseDuration, String courseDetails, String paymentStatus) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.courseType = courseType;
        this.courseDuration = courseDuration;
        this.courseDetails = courseDetails;
        this.paymentStatus = paymentStatus;
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

    public String getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(String courseDetails) {
        this.courseDetails = courseDetails;
    }


    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }


    public String getCourseDuration() {
        return courseDuration;
    }


    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterCourse)) return false;
        RegisterCourse that = (RegisterCourse) o;
        return getCourseId() == that.getCourseId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourseId());
    }

    @Override
    public String toString() {
        return "\n RegisterCourse{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseFee=" + courseFee +
                ", courseType='" + courseType + '\'' +
                ", courseDuration='" + courseDuration + '\'' +
                ", courseDetails='" + courseDetails + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
