package com.shu.util;

import org.json.JSONObject;

/**
 * Created by Dell on 2017/5/17.
 */
public class Status extends JSONObject {
    public Status(){
    }

    public Status(boolean b){
        this.put("status", b);
    }
}
