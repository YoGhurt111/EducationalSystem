package com.shu.dao;

import com.shu.entity.Student;
import com.shu.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2017/4/9.
 */
@Repository
public class StudentDao extends BaseDao {

    public List<Student> selectStudent(String username, String pwd){
        List<Student> studentList = this.getSesstion().createQuery("from Student where id=? and password=?" )
                .setParameter(0, username)
                .setParameter(1, pwd)
                .list();
        return studentList;
    }

    public Student getStudentById(String id){
        Student student = this.getSesstion().get(Student.class,id);
        return student;
    }

    public List<Student> getAll(){
        return this.getSesstion().createQuery("from Student").list();
    }

    public String delete(Student student){
        try {
            this.getSesstion().delete(student);
            return new JSONObject().put("status", "success").toString();
        }catch (Exception e){
            return new JSONObject().put("status", "fail").toString();
        }
    }

    public void save(Student student){
        this.getSesstion().save(student);
    }
}
