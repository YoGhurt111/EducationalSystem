package com.shu.dao;

import com.shu.entity.Student;
import com.shu.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dell on 2017/4/10.
 */
@Repository
public class TeacherDao extends BaseDao{

    public List<Teacher> selectTeacher(String username, String pwd){
        List<Teacher> teacherList = this.getSesstion().createQuery("from Teacher where id=? and password=?" )
                .setParameter(0, username)
                .setParameter(1, pwd)
                .list();
        return teacherList;
    }
}
