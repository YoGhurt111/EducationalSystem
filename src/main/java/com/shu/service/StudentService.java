package com.shu.service;

import com.shu.dao.DepartmentDao;
import com.shu.dao.StudentDao;
import com.shu.entity.Department;
import com.shu.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dell on 2017/4/10.
 */
@Service
public class StudentService {
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    StudentDao studentDao;

    public Department getDepartment(String studentId){
        String departmentId = studentDao.getStudentById(studentId)
                .getDepartmentId();
        return departmentDao.getDepartmentById(departmentId);
    }

    public Student getStudent(String studentId){
        return studentDao.getStudentById(studentId);
    }
}
