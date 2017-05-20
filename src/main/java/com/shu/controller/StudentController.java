package com.shu.controller;

import com.shu.service.StudentService;
import org.json.Cookie;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Dell on 2017/4/17.
 */
@Controller
@RequestMapping(value = "student")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "")
    public String index() {
        return "student";
    }

    @RequestMapping(value = "/data",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getStudentData(@CookieValue(value = "id") String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name",studentService.getStudent(id).getName());
        jsonObject.put("department",studentService.getDepartment(id).getName());
        return jsonObject.toString();
    }

    @RequestMapping(value = "/courseTable")
    public String courseTable(){
        return "courseTable";
    }

    @RequestMapping(value = "/gradeTable")
    public String gradeTable(){
        return "grade";
    }

    @RequestMapping(value = "/get_courseList",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getCourseList(@CookieValue(value = "id") String id){
        return studentService.getCourseList(id);
    }

    @RequestMapping(value = "/get_grade",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getGrade(@CookieValue(value = "id")String id){
        return studentService.getGrade(id);
    }

    @RequestMapping(value = "/get_total_credit")
    @ResponseBody
    public String getTCredit(@CookieValue(value = "id")String id){
        return studentService.getTotalCredit(id);
    }
}
