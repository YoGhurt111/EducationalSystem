package com.shu.service;

import com.shu.dao.StudentDao;
import com.shu.dao.TeacherDao;
import com.shu.entity.STC;
import com.shu.entity.Student;
import com.shu.entity.TC;
import com.shu.entity.Teacher;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTMLDocument;

import java.util.Iterator;
import java.util.Set;

import static com.shu.util.SecurityTools.nullCheck;
import static com.shu.util.TermTools.getCurrentTerm;
import static com.shu.util.TermTools.getCurrentYear;

/**
 * Created by Dell on 2017/4/10.
 */
@Service
public class TeacherService {
    @Autowired
    TeacherDao teacherDao;

    public String getTeacherInfo(String id){
        Teacher teacher = teacherDao.getTeacherById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", nullCheck(teacher.getId()));
        jsonObject.put("name", nullCheck(teacher.getName()));
        jsonObject.put("department", nullCheck(teacher.getDepartment().getName()));
        jsonObject.put("d_location", nullCheck(teacher.getDepartment().getLocation()));
        return jsonObject.toString();

    }

    public String getStudentList(String id){
        Teacher teacher = teacherDao.getTeacherById(id);
        JSONArray jsonArray = new JSONArray();
        int count = 1;
        //获取当前学期
        JSONObject jsonObject = new JSONObject(getCurrentTerm());
        String term = (String) jsonObject.get("term");

        for (TC tc : teacher.getTcs()){
            for (STC stc : tc.getStcs()){
                if (stc.getTerm().substring(0,4).equals(getCurrentYear()) && stc.getTerm().substring(4).equals(term)){
                    JSONObject jsonData = new JSONObject();
                    jsonData.put("no", count);
                    count++;
                    jsonData.put("s_id", nullCheck(stc.getStudent().getId()));
                    jsonData.put("s_name", nullCheck(stc.getStudent().getName()));
                    jsonData.put("department", nullCheck(stc.getStudent().getDepartment().getName()));
                    jsonData.put("c_id", nullCheck(tc.getCourse().getId()));
                    jsonData.put("c_name", nullCheck(tc.getCourse().getName()));
                    jsonArray.put(jsonData);
                }
            }
        }
        return new JSONObject().put("data", nullCheck(jsonArray)).toString();
    }

    public String getCourseList(String id){
        Teacher teacher = teacherDao.getTeacherById(id);
        JSONArray jsonArray = new JSONArray();
        int count = 0;
        //获取当前学期
        JSONObject jsonObject = new JSONObject(getCurrentTerm());
        String term = (String) jsonObject.get("term");
        String[] str = {"A","B","C","D","E","F","G","H","I","J","K"};
        for (TC tc : teacher.getTcs()){
            for (STC stc : tc.getStcs()){
                if (stc.getTerm().substring(0,4).equals(getCurrentYear()) && stc.getTerm().substring(4).equals(term)){
                    JSONObject jsonData = new JSONObject();
                    jsonData.put("no", nullCheck(str[count]));
                    count++;
                    jsonData.put("c_id", nullCheck(tc.getCourse().getId()));
                    jsonData.put("c_name", nullCheck(tc.getCourse().getName()));
                    jsonData.put("credit", nullCheck(tc.getCourse().getCredit()));
                    jsonData.put("time", "周"+nullCheck(tc.getTime().substring(0,1)+tc.getTime().substring(1))+"节");
                    jsonData.put("location", nullCheck(tc.getLocation()));
                    jsonArray.put(jsonData);
                    break;
                }
            }
        }
        return new JSONObject().put("data", jsonArray).toString();
    }
}
