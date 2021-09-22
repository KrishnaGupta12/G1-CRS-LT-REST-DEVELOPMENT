package com.lt.bean;

import java.util.List;

public class CourseCatalog {
    private long courseId;
    private long professorId;
    private long courseCatalogId;
    private List<Courses> coursesList;

    public CourseCatalog() {
    }

    public CourseCatalog(long courseId, long professorId, long courseCatalogId, List<Courses> coursesList) {
        this.courseId = courseId;
        this.professorId = professorId;
        this.courseCatalogId = courseCatalogId;
        this.coursesList = coursesList;
    }


    public List<Courses> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Courses> coursesList) {
        this.coursesList = coursesList;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    public long getCourseCatalogId() {
        return courseCatalogId;
    }

    public void setCourseCatalogId(long courseCatalogId) {
        this.courseCatalogId = courseCatalogId;
    }
}
