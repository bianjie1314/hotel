package com.syshotel.common;

import com.syshotel.pojo.vo.PageViewVo;

import java.io.Serializable;

/**
 * 公共返回的结果bean
 */
public class CommonResult implements Serializable {

    //错误编码
    private String errorCode;
    //消息
    private String msg;
    //是否成功
    private boolean result;
    //数据
    private Object data;


    public static CommonResult  ERROR(String msg){
        return new CommonResult(msg,false);
    }

    public static CommonResult  ERROR(String errorCode,String msg){
        return new CommonResult(errorCode,msg,false);
    }

    public static CommonResult  SUCCESS(Object data){
        return new CommonResult(null,true,data);
    }

    public static CommonResult  SUCCESS(int size,Object data){
        return new CommonResult(new PageViewVo(size,data));
    }

    public static CommonResult  SUCCESS(String msg,Object data){
        return new CommonResult(msg,true,data);
    }

    public CommonResult(Object data) {
        this.result = true;
        this.data = data;
    }

  /*  public CommonResult(String msg) {
        this.result = false;
        this.msg = msg;
    }*/

    public CommonResult(String errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.result = false;
    }

    public CommonResult(String msg, boolean result) {
        this.msg = msg;
        this.result = result;
    }

    public CommonResult(String msg, boolean result, Object data) {
        this.msg = msg;
        this.result = result;
        this.data = data;
    }

    public CommonResult(String errorCode, String msg, boolean result) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
