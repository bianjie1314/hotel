package com.syshotel.utils;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {


    /**
     * 将对象转换为json字符串
     * @param obj : Object
     * @return : String
     */
    public static String objToJsonString(Object obj){
        return JSONObject.toJSONString(obj);
    }

    /**
     * 将json字符串转换为对象
     * @param jsonString ： String
     * @return ： Object
     */
    public static Object jsonStringToObj(String jsonString,Class cls){
        return JSONObject.parseObject(jsonString,cls);
    }


}
