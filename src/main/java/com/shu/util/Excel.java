package com.shu.util;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Dell on 2017/5/25.
 */
public class Excel {
    public static JSONObject createExcel(String path, JSONArray jsonArray) throws IOException {
        JSONObject jsonObject = new JSONObject();
        if(jsonArray.length() == 0){
            jsonObject.put("result", "fail");
            jsonObject.put("reason", "data error");
            return jsonObject;
        }
        File file = new File(path);
        OutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(outputStream);
            WritableSheet sheet = writableWorkbook.createSheet("登分表", 1);// 创建新的一页
            Label label; // 单元格对象
            int col = 0; // 列数计数
            String[] labelName = {"课程编号", "课程名称", "学分",
                    "学生编号", "学生姓名", "平时成绩", "期末成绩"};
            //为Excel表格在第0行上添加标签
            for (String name : labelName){
                label = new Label(col, 0, name);
                col++;
                sheet.addCell(label);
            }
            String[] key = {"c_id", "c_name", "credit", "s_id",
                    "s_name", "u_grade", "f_grade"};
            //插入数据
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject item = jsonArray.getJSONObject(i);
                col = 0;
                for (String data : key){
                    String value = item.get(data).toString();
                    label = new Label(col, i+1, value);
                    col++;
                    sheet.addCell(label);
                }
            }
            writableWorkbook.write();
            writableWorkbook.close();
        }catch (Exception e){
            jsonObject.put("result", "fail");
            jsonObject.put("reason", e.getMessage());
            return jsonObject;
        }finally {
            assert outputStream != null;
            outputStream.close();
        }
        jsonObject.put("result", "success");
        return jsonObject;
    }

    public static JSONArray toJSONArray(MultipartFile file){
        JSONArray jsonArray = new JSONArray();
        try {
            Workbook workbook = Workbook.getWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet(0);
            int n = sheet.getRows();
            for (int i = 1; i < n; i++){
                JSONObject data = new JSONObject();
                data.put("c_id", sheet.getCell(0,i).getContents());
                data.put("s_id", sheet.getCell(3,i).getContents());
                data.put("u_grade", sheet.getCell(5,i).getContents());
                data.put("f_grade", sheet.getCell(6,i).getContents());
                jsonArray.put(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return jsonArray;
    }

}
