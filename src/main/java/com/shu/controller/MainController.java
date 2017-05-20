package com.shu.controller;

import com.shu.entity.Admin;
import com.shu.entity.Student;
import com.shu.entity.Teacher;
import com.shu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.Servlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpCookie;

/**
 * Created by Dell on 2017/3/31.
 */
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/selectUser")
    public String selectUser(HttpServletResponse response, String username, String pwd) {

        if (username.length() == 4 && userService.adminExist(username, pwd)) {
            Cookie cookie = new Cookie("id", username);
            cookie.setMaxAge(60 * 60 * 24 * 3);
            response.addCookie(cookie);
            return "redirect:/admin";
        } else if (username.length() == 8 && userService.studentExist(username, pwd)) {
            Cookie cookie = new Cookie("id", username);
            cookie.setMaxAge(60 * 60 * 24 * 3);
            response.addCookie(cookie);
            return "redirect:/student";

        } else if (username.length() == 10 && userService.teacherExist(username, pwd)) {

            Cookie cookie = new Cookie("id", username);
            cookie.setMaxAge(60 * 60 * 24 * 3);
            response.addCookie(cookie);
            return "redirect:/teacher";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return "redirect:/";
    }
}
