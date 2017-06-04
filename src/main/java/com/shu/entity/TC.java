package com.shu.entity;

import org.codehaus.jackson.map.annotate.JacksonInject;
import org.hibernate.mapping.AuxiliaryDatabaseObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dell on 2017/5/18.
 */
@Entity
public class TC implements Serializable{
    private int tc_id;
    private String time;
    private String location;
    private Teacher teacher;
    private Course course;
    private Set<STC> stcs = new HashSet<STC>();

    @OneToMany(mappedBy = "tc")
    public Set<STC> getStcs() {
        return stcs;
    }

    public void setStcs(Set<STC> stcs) {
        this.stcs = stcs;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getTc_id() {
        return tc_id;
    }

    public void setTc_id(int tc_id) {
        this.tc_id = tc_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "t_id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "c_id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
