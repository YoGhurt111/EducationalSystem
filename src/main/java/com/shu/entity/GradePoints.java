package com.shu.entity;

import javax.persistence.*;

/**
 * Created by Dell on 2017/5/17.
 */
@Entity
public class GradePoints {
    private int g_id;
    private String term;
    private double gpa;
    private Student student;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "s_id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
