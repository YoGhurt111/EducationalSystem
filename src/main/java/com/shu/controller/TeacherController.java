package com.shu.controller;

import com.shu.service.TeacherService;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import static com.shu.util.Excel.createExcel;

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

    @RequestMapping(value = "/updateGrade")
    public String updateGrade(){
        return "updateGrade";
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

    @RequestMapping(value = "/getGradeTable", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getGradeTable(@CookieValue(value = "id")String id){
        return teacherService.getGradeTable(id);
    }


    @RequestMapping(value = "/downloadSTC", produces = "text/html;charset=utf-8")
    @ResponseBody
    public ResponseEntity<byte[]> stcExcel(@CookieValue(value = "id")String id,
                                           HttpServletRequest request) throws IOException{
        String path = request.getSession().getServletContext().getRealPath("/" + "a.xls");
        JSONArray jsonArray = new JSONObject(teacherService.getGradeTable(id)).getJSONArray("data");
        System.out.println(jsonArray.toString());
        JSONObject status = createExcel(path, jsonArray);
        if (status.get("result").equals("success")){
            File file = new File(path);
            String fileName = new String((teacherService.getTeacherNameById(id)+ "老师的登分表.xls").getBytes("utf-8"),"iso-8859-1");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.CREATED);
        }else {
            return null;
        }
    }

    @RequestMapping(value = "/uploadSTC", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String upload(@RequestParam(value = "uploadfile", required = false) MultipartFile file, @CookieValue(value = "id")String id){
        String s = teacherService.uploadSTC(file,id);
        return s;
    }

}
