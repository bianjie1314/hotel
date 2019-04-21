package com.syshotel.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderCodeUtil {

    /**
     * 获取编号
     * @return String
     */
    public static String getOrderCode(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date())+System.currentTimeMillis();
    }
}
