package com.shu.service;

import com.shu.dao.AdminDao;
import com.shu.dao.CourseDao;
import com.shu.dao.StudentDao;
import com.shu.entity.Admin;
import com.shu.entity.Course;
import com.shu.entity.Student;
import jxl.Sheet;
import jxl.Workbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.shu.util.SecurityTools.nullCheck;

/**
 * Created by Dell on 2017/4/10.
 */
@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private StudentDao studentDao;

    public String getAdminInfo(String id){
        Admin admin = adminDao.getAdminById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", nullCheck(admin.getName()));
        jsonObject.put("id", nullCheck(admin.getId()));
        return jsonObject.toString();
    }

    public String getStudentList(){
        List<Student> students = studentDao.getAll();
        JSONArray jsonArray = new JSONArray();
        for (Student student : students){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("s_id", nullCheck(student.getId()));
            jsonObject.put("s_name", nullCheck(student.getName()));
            jsonArray.put(jsonObject);
        }
        return new JSONObject().put("data", jsonArray).toString();
    }

    public String deleteStudent(String jsonStr){
        JSONObject jsonObject = new JSONObject(jsonStr);
        Student student = studentDao.getStudentById(jsonObject.get("s_id").toString());
        return studentDao.delete(student);
    }

    public String upload(MultipartFile file){
        JSONObject status = new JSONObject();
        try {
            Workbook workbook = Workbook.getWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet(0);
            int n = sheet.getRows();
            for (int i = 1; i < n; i++){
                Student student = new Student();
                student.setId(sheet.getCell(0,i).getContents());
                student.setName(sheet.getCell(1,i).getContents());
                student.setPassword(sheet.getCell(2,i).getContents());
                studentDao.save(student);
            }
        } catch (Exception e) {
            return status.put("status", "fail").toString();
        }
        return status.put("status", "success").toString();
    }
}
