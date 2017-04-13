package com.shu.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dell on 2017/4/9.
 */
@Entity
@Table(name = "student")
public class Student {
    private String id;
    private String name;
    private String password;
    private String departmentId;
    private Set<OfferedCourse> offeredCourseSet = new HashSet<OfferedCourse>();

    @Id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "department_id")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != null ? !id.equals(student.id) : student.id != null) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (password != null ? !password.equals(student.password) : student.password != null) return false;
        if (departmentId != null ? !departmentId.equals(student.departmentId) : student.departmentId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        return result;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "grade",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "offered_course_id")
    )
    public Set<OfferedCourse> getOfferedCourseSet() {
        return offeredCourseSet;
    }

    public void setOfferedCourseSet(Set<OfferedCourse> offeredCourseSet) {
        this.offeredCourseSet = offeredCourseSet;
    }
}
