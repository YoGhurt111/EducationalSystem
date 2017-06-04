package com.shu.dao;

import com.shu.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Dell on 2017/5/25.
 */
@Repository
public class CourseDao extends BaseDao{
    public Course getCourseById(String id){
        return this.getSesstion().get(Course.class, id);
    }

    public List<Course> getALL(){
        return this.getSesstion().createQuery("from Course").list();
    }
}
