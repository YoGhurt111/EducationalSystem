package com.shu.service;

import com.shu.dao.StudentDao;

import com.shu.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private StudentDao studentDao;

    public boolean studentExist(String username, String pwd){
        return !studentDao.selectStudent(username, pwd).isEmpty();
    }

    public Student getStudent(String username, String pwd){
        return studentDao.selectStudent(username, pwd).get(0);
    }
}
