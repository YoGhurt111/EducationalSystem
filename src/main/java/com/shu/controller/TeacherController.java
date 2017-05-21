package com.shu.controller;

import com.shu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Dell on 2017/4/20.
 */
@Controller
@RequestMapping(value = "teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "")
    public String index(){
        return "teacher";
    }

    @RequestMapping(value = "/studentList")
    public String studentList(){
        return "checkStudent";
    }

    @RequestMapping(value = "/courseList")
    public String courseList(){
        return "teacherCourse";
    }

    @RequestMapping(value = "/data", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getTeacherInfo(@CookieValue(value = "id")String id){
        return teacherService.getTeacherInfo(id);
    }

    @RequestMapping(value = "/getStudentList", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getStudentList(@CookieValue(value = "id")String id){
        return teacherService.getStudentList(id);
    }

    @RequestMapping(value = "/getCourseList", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getCourseList(@CookieValue(value = "id") String id){
        return teacherService.getCourseList(id);
    }

}
