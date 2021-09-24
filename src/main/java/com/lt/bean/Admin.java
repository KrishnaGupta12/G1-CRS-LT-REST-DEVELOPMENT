package com.lt.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
	@Id
    private long adminId;
    private String adminName;
    private String adminEmail;
    private String adminGender;
    private Date adminDOB;
    private long adminContactNo;
    private long courseId;

    public Admin() {
    }

    public Admin(long adminId, String adminName, String adminEmail, String adminGender, Date adminDOB, long adminContactNo, long courseId) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminGender = adminGender;
        this.adminDOB = adminDOB;
        this.adminContactNo = adminContactNo;
        this.courseId = courseId;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminGender() {
        return adminGender;
    }

    public void setAdminGender(String adminGender) {
        this.adminGender = adminGender;
    }

    public Date getAdminDOB() {
        return adminDOB;
    }

    public void setAdminDOB(Date adminDOB) {
        this.adminDOB = adminDOB;
    }

    public long getAdminContactNo() {
        return adminContactNo;
    }

    public void setAdminContactNo(long adminContactNo) {
        this.adminContactNo = adminContactNo;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
