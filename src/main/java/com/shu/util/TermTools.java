package com.shu.util;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dell on 2017/5/17.
 */
public class TermTools {
    public static String getCurrentTerm(){
        SimpleDateFormat df = new SimpleDateFormat("MM");
        int now = Integer.parseInt(df.format(new Date()));
        JSONObject jsonObject = new JSONObject();
        if (now >= 3 && now <= 5) {
            jsonObject.put("term", "spring");
            return jsonObject.toString();
        } else if (now == 6) {
            jsonObject.put("term", "summer");
            return jsonObject.toString();
        } else if (now >= 8 && now <= 10) {
            jsonObject.put("term", "autumn");
            return jsonObject.toString();
        }else if ((now >= 11 && now <=12)||now==1){
            jsonObject.put("term", "winter");
            return jsonObject.toString();
        }else {
            jsonObject.put("term","vacation");
            return jsonObject.toString();
        }
    }

    public static String getCurrentYear(){
        //获取当前年份
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String thisYear = dateFormat.format(new Date());
        return thisYear;
    }
}
