package com.shu.controller;

import com.shu.service.AdminService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.shu.util.Excel.createExcel;

/**
 * Created by Dell on 2017/4/20.
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "")
    public String index() {
        return "admin";
    }

    @RequestMapping(value = "/student_manage")
    public String courseManage() {
        return "studentManage";
    }

    @RequestMapping(value = "admin_info", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getAdminInfo(@CookieValue(value = "id") String id) {
        return adminService.getAdminInfo(id);
    }

    @RequestMapping(value = "get_student_list", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getStudentList() {
        return adminService.getStudentList();
    }

    @RequestMapping(value = "delete_student", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteStudent(@RequestParam(value = "jsonData") String data) {
        return adminService.deleteStudent(data);
    }

    @RequestMapping(value = "/download", produces = "text/html;charset=utf-8")
    @ResponseBody
    public ResponseEntity<byte[]> studentExcel(HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/" + "a.xls");
        File file = new File(path);
        OutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(outputStream);
            WritableSheet sheet = writableWorkbook.createSheet("登分表", 1);// 创建新的一页
            Label label; // 单元格对象
            int col = 0; // 列数计数
            String[] labelName = {"学生编号", "学生姓名", "密码"};
            //为Excel表格在第0行上添加标签
            for (String name : labelName) {
                label = new Label(col, 0, name);
                col++;
                sheet.addCell(label);
            }
            writableWorkbook.write();
            writableWorkbook.close();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
        String fileName = new String(("学生注册表.xls").getBytes("utf-8"), "iso-8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/upload", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String upload(@RequestParam(value = "uploadfile", required = false) MultipartFile file){
        return adminService.upload(file);
    }
}
