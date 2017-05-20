package com.shu.entity;

import javax.persistence.*;

/**
 * Created by Dell on 2017/5/11.
 */
@Entity
public class STC {
    private int stc_id;
    private Double usualGrades;
    private Double finalGrades;
    private String term;
    private Student student;
    private TC tc;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "tc_id")
    public TC getTc() {
        return tc;
    }

    public void setTc(TC tc) {
        this.tc = tc;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getStc_id() {
        return stc_id;
    }

    public void setStc_id(int stc_id) {
        this.stc_id = stc_id;
    }

    @Column(nullable = true)
    public Double getUsualGrades() {
        return usualGrades;
    }

    public void setUsualGrades(Double usualGrades) {
        this.usualGrades = usualGrades;
    }

    @Column(nullable = true)
    public Double getFinalGrades() {
        return finalGrades;
    }

    public void setFinalGrades(Double finalGrades) {
        this.finalGrades = finalGrades;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "s_id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
