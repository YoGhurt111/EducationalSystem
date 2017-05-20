package com.shu.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dell on 2017/4/9.
 */
@Entity
public class Student {
    private String id;
    private String name;
    private String password;
    private Department department;
    private Set<STC> STCS = new HashSet<STC>();
    private Set<GradePoints> gradePoints = new HashSet<GradePoints>();


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    public Set<GradePoints> getGradePoints() {
        return gradePoints;
    }

    public void setGradePoints(Set<GradePoints> gradePoints) {
        this.gradePoints = gradePoints;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    public Set<STC> getSTCS() {
        return STCS;
    }

    public void setSTCS(Set<STC> STCS) {
        this.STCS = STCS;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "d_id")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Id
    @Column(name = "s_id")
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


}
