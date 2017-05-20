package com.shu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Dell on 2017/4/20.
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController {
    @RequestMapping(value = "")
    public String index(){
        return "admin";
    }
}
