package com.syshotel.utils;

import java.text.DecimalFormat;

/**
 * 数字格式化工具类
 */
public class NumberUtil {


    /**
     * 格式化double保留两位小数点
     * @param data
     * @return
     */
    public static double formateDoubleWithTwoPoint(double data){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String formatStr = decimalFormat.format(data);
        return Double.parseDouble(formatStr);
    }

}
