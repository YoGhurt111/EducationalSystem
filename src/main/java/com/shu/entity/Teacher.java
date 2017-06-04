package com.shu.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dell on 2017/4/9.
 */
@Entity
public class Teacher implements Serializable{
    private String id;
    private String name;
    private String password;
    private Department department;
    private Set<TC> tcs = new HashSet<TC>();

    @OneToMany(mappedBy = "teacher")
    public Set<TC> getTcs() {
        return tcs;
    }

    public void setTcs(Set<TC> tcs) {
        this.tcs = tcs;
    }

    @Id
    @Column(name = "t_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "d_id")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
