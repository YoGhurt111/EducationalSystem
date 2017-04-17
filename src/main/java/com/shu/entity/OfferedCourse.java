package com.shu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dell on 2017/4/9.
 */
@Entity
@Table(name = "offered_course")
public class OfferedCourse {
    private int id;
    private String courseId;
    private String teacherId;
    private String term;
    private String courseLocation;
    private String courseTime;
    private int capacity;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "course_id")
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Column(name = "teacher_id")
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Column(name = "course_location")
    public String getCourseLocation() {
        return courseLocation;
    }

    public void setCourseLocation(String courseLocation) {
        this.courseLocation = courseLocation;
    }

    @Column(name = "course_time")
    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferedCourse that = (OfferedCourse) o;

        if (id != that.id) return false;
        if (capacity != that.capacity) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (teacherId != null ? !teacherId.equals(that.teacherId) : that.teacherId != null) return false;
        if (term != null ? !term.equals(that.term) : that.term != null) return false;
        if (courseLocation != null ? !courseLocation.equals(that.courseLocation) : that.courseLocation != null)
            return false;
        if (courseTime != null ? !courseTime.equals(that.courseTime) : that.courseTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (teacherId != null ? teacherId.hashCode() : 0);
        result = 31 * result + (term != null ? term.hashCode() : 0);
        result = 31 * result + (courseLocation != null ? courseLocation.hashCode() : 0);
        result = 31 * result + (courseTime != null ? courseTime.hashCode() : 0);
        result = 31 * result + capacity;
        return result;
    }
}
