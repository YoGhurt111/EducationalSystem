package com.shu.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dell on 2017/4/9.
 */
@Entity
public class Course implements Serializable{
    private String id;
    private String name;
    private int credit;
    private Set<TC> tcs = new HashSet<TC>();

    @OneToMany(mappedBy = "course")
    public Set<TC> getTcs() {
        return tcs;
    }

    public void setTcs(Set<TC> tcs) {
        this.tcs = tcs;
    }

    @Id
    @Column(name = "c_id")
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

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

}
