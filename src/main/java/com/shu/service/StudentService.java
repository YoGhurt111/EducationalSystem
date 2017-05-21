package com.shu.service;

import com.shu.dao.DepartmentDao;
import com.shu.dao.StudentDao;
import com.shu.entity.*;
import com.shu.util.GPACounter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.shu.util.GPACounter.countGPA;
import static com.shu.util.TermTools.getCurrentTerm;
import static com.shu.util.SecurityTools.nullCheck;
import static com.shu.util.TermTools.getCurrentYear;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Dell on 2017/4/10.
 */
@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;


    public Department getDepartment(String studentId) {
        return studentDao.getStudentById(studentId).getDepartment();
    }

    public Student getStudent(String studentId) {
        return studentDao.getStudentById(studentId);
    }

    public String getCourseList(String id) {
        Student student = studentDao.getStudentById(id);

        //初始化工作
        String[] str = {"A","B","C","D","E","F","G","H","I","J","K"};
        int i = 0;
        JSONArray array = new JSONArray();

        //获取某学生的选课记录
        Set<STC> STCSet = student.getSTCS();
        Iterator<STC> iterator = STCSet.iterator();

        //获取当前学期
        JSONObject jsonObject = new JSONObject(getCurrentTerm());
        String term = (String) jsonObject.get("term");


        while (iterator.hasNext()) {
            STC stc = (STC) iterator.next();
            String time = stc.getTerm();
            //如果选课记录中前四位匹配当前年份，后若干位匹配当前学期，则匹配需求
            if (time.substring(0, 4).equals(getCurrentYear()) && time.substring(4).equals(term)) {
                JSONObject json = new JSONObject();
                json.put("no", nullCheck(str[i]));
                i++;
                Course course = stc.getTc().getCourse();
                json.put("c_id", nullCheck(course.getId()));
                json.put("c_name", nullCheck(course.getName()));
                json.put("credit", nullCheck(course.getCredit()));
                TC tc = stc.getTc();
                String temp = tc.getTime();
                json.put("time", "周"+nullCheck(temp.substring(0,1)+temp.substring(1))+"节");
                json.put("location", nullCheck(tc.getLocation()));
                Teacher teacher = tc.getTeacher();
                json.put("t_id", nullCheck(teacher.getId()));
                json.put("t_name", nullCheck(teacher.getName()));
                array.put(json);
            }
        }
        return new JSONObject().put("data",nullCheck(array)).toString();
    }

    public String getGrade(String id){
        Student student = studentDao.getStudentById(id);

        //初始化工作
        String[] str = {"A","B","C","D","E","F","G","H","I","J","K"};
        int i = 0;
        JSONArray array = new JSONArray();

        //获取某学生的选课记录
        Set<STC> STCSet = student.getSTCS();
        Iterator<STC> iterator = STCSet.iterator();

        //获取当前学期
        JSONObject jsonObject = new JSONObject(getCurrentTerm());
        String term = (String) jsonObject.get("term");

        while (iterator.hasNext()) {
            STC stc = (STC) iterator.next();
            String time = stc.getTerm();
            //如果选课记录中前四位匹配当前年份，后若干位匹配当前学期，则匹配需求
            if (time.substring(0, 4).equals(getCurrentYear()) && time.substring(4).equals(term)) {
                JSONObject json = new JSONObject();
                json.put("grade", stc.getFinalGrades()*0.6 + stc.getUsualGrades()*0.4);
                json.put("gpa", countGPA(stc.getFinalGrades(),stc.getUsualGrades(),0.6));
                json.put("no", str[i]);
                i++;
                Course course = stc.getTc().getCourse();
                json.put("c_id", course.getId());
                json.put("c_name", nullCheck(course.getName()));
                TC tc = stc.getTc();
                Teacher teacher = tc.getTeacher();
                json.put("t_id", nullCheck(teacher.getId()));
                json.put("t_name", nullCheck(teacher.getName()));
                json.put("credit", course.getCredit());
                array.put(json);
            }
        }
        return new JSONObject().put("data",array).toString();
    }
    public String getTotalCredit(String id){
        Student student = studentDao.getStudentById(id);
        //获取某学生的选课记录
        Set<STC> STCSet = student.getSTCS();
        Iterator<STC> iterator = STCSet.iterator();

        //获取当前学期
        JSONObject jsonObject = new JSONObject(getCurrentTerm());
        String term = (String) jsonObject.get("term");

        //初始化
        int count = 0;
        JSONObject data = new JSONObject();
        int totalCredit = 0;
        double totalGrade = 0;

        while (iterator.hasNext()) {
            STC stc = (STC) iterator.next();
            String time = stc.getTerm();
            //如果选课记录中前四位匹配当前年份，后若干位匹配当前学期，则匹配需求
            if (time.substring(0, 4).equals(getCurrentYear()) && time.substring(4).equals(term)) {
                totalGrade += stc.getFinalGrades()*0.6 + stc.getUsualGrades()*0.4;
                totalCredit += stc.getTc().getCourse().getCredit();
                count += 1;
            }
        }
        data.put("gpa", nullCheck(countGPA(totalGrade/count)));
        data.put("t_credit", nullCheck(totalCredit));
        return data.toString();
    }
}
