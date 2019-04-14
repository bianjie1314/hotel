package com.syshotel.common;

public class SearchVo {

    private String text;	//输入文本
    private String startTime;	//起始时间
    private String endTime;		//结束时间
    private String category;		//查询类型
    private double startNum;
    private double endNum;
    private int index;	//下标,用于在数据对比中使用
    private int status; //状态
    private int userFlag;//用户标识

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getStartNum() {
        return startNum;
    }

    public void setStartNum(double startNum) {
        this.startNum = startNum;
    }

    public double getEndNum() {
        return endNum;
    }

    public void setEndNum(double endNum) {
        this.endNum = endNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(int userFlag) {
        this.userFlag = userFlag;
    }
}
