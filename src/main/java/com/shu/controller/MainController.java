package com.shu.controller;

import com.shu.entity.Admin;
import com.shu.entity.Student;
import com.shu.entity.Teacher;
import com.shu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dell on 2017/3/31.
 */
@Controller
public class MainController {
    @Autowired private UserService userService;

    @RequestMapping(value = "/")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/selectUser")
    public ModelAndView selectUser(String username, String pwd){
        ModelAndView modelAndView = new ModelAndView();
        if (username.length() == 4){
            if (userService.adminExist(username, pwd)){
                Admin admin = userService.getAdmin(username, pwd);
                modelAndView.addObject(admin);
                modelAndView.setViewName("admin");
            }
            else{
                modelAndView.setViewName("login");
                return modelAndView;
            }
        }
        else if (username.length() == 8){
            if (userService.studentExist(username, pwd)){
                Student student = userService.getStudent(username, pwd);
                modelAndView.addObject(student);
                modelAndView.setViewName("student");
            }
            else{
                modelAndView.setViewName("login");
                return modelAndView;
            }
        }
        else if (username.length() == 10){
            if (userService.teacherExist(username, pwd)){
                Teacher teacher = userService.getTeacher(username, pwd);
                modelAndView.addObject(teacher);
                modelAndView.setViewName("teacher");
            }
            else{
                modelAndView.setViewName("login");
                return modelAndView;
            }
        }
        return modelAndView;
    }
}
