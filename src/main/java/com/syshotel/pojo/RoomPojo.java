package com.syshotel.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RoomPojo implements Serializable {

    private int id;
    //房间编号
    private String number;
    //状态,1正常，2使用中，3下线
    private int status;
    //酒店编号
    private int hotelId;
    //房间面积
    private double area;
    //是否有窗户:1有，2没有
    private int window;
    //是否有独卫1有，2没有
    private int toilet;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //房费
    private double money;
    //押金
    private double deposit;
    //图片
    private String pictureIds;
    //酒店
    private HotelPojo hotel;
    //图片信息
    private List<PicturePojo> pictures;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }

    public int getToilet() {
        return toilet;
    }

    public void setToilet(int toilet) {
        this.toilet = toilet;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public HotelPojo getHotel() {
        return hotel;
    }

    public void setHotel(HotelPojo hotel) {
        this.hotel = hotel;
    }

    public String getPictureIds() {
        return pictureIds;
    }

    public void setPictureIds(String pictureIds) {
        this.pictureIds = pictureIds;
    }

    public List<PicturePojo> getPictures() {
        return pictures;
    }

    public void setPictures(List<PicturePojo> pictures) {
        this.pictures = pictures;
    }
}
