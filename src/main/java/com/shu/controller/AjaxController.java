package com.shu.controller;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.beans.SimpleBeanInfo;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.shu.util.TermTools.getCurrentTerm;

/**
 * Created by Dell on 2017/5/16.
 */
@RestController
@RequestMapping(value = "ajax")
public class AjaxController {
    /**
     * 通过比较当前月份与规定月份的比较获得学期数据
     * 3月-5月：春季学期
     * 6月：夏季学期
     * 8月-10月：秋季学期
     * 11月-1月：冬季学期
     *
     * @return 含有学期信息的JSON字符串 例：{'term':'summer'}
     */
    @RequestMapping(value = "/term")
    public String getTerm() {
        return getCurrentTerm();
    }
}
