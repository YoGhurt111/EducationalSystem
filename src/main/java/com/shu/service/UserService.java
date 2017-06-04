package com.shu.service;

import com.shu.dao.*;

import com.shu.entity.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shu.util.SecurityTools.nullCheck;

@Service
public class UserService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private TCDao tcDao;
    @Autowired
    private CourseDao courseDao;


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

    public String test(){
        return "test";
    }

    public String getTC(){
        List<TC> tcs = tcDao.getAll();
        JSONArray jsonArray = new JSONArray();
        for (TC tc : tcs){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("location", nullCheck(tc.getLocation()));
            jsonObject.put("time", "周"+nullCheck(tc.getTime().substring(0,1)+tc.getTime().substring(1))+"节");
            jsonObject.put("c_id", nullCheck(tc.getCourse().getId()));
            jsonObject.put("c_name", nullCheck(tc.getCourse().getName()));
            jsonObject.put("credit", nullCheck(tc.getCourse().getCredit()));
            jsonObject.put("t_id", nullCheck(tc.getTeacher().getId()));
            jsonObject.put("t_name", nullCheck(tc.getTeacher().getName()));
            jsonArray.put(jsonObject);
        }
        return new JSONObject().put("data", jsonArray).toString();
    }
}
