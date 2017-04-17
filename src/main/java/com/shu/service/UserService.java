package com.shu.service;

import com.shu.dao.AdminDao;
import com.shu.dao.StudentDao;

import com.shu.dao.TeacherDao;
import com.shu.entity.Admin;
import com.shu.entity.Student;
import com.shu.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private AdminDao adminDao;

    public boolean studentExist(String username, String pwd) {
        return !studentDao.selectStudent(username, pwd).isEmpty();
    }

    public Student getStudent(String username, String pwd) {
        return studentDao.selectStudent(username, pwd).get(0);
    }

    public boolean teacherExist(String username, String pwd) {
        return !teacherDao.selectTeacher(username, pwd).isEmpty();
    }

    public Teacher getTeacher(String username, String pwd) {
        return teacherDao.selectTeacher(username, pwd).get(0);
    }

    public boolean adminExist(String username, String pwd) {
        return !adminDao.selectAdmin(username, pwd).isEmpty();
    }

    public Admin getAdmin(String username, String pwd) {
        return adminDao.selectAdmin(username, pwd).get(0);
    }
}
