package com.shu.dao;

import com.shu.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dell on 2017/4/10.
 */
@Repository
public class DepartmentDao extends BaseDao{

    public Department getDepartmentById(String id){
        Department department = this.getSesstion().get(Department.class,id);
        return department;
    }
}
