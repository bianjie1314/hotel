package com.syshotel.pojo;


import java.io.Serializable;
import java.util.Date;


/**
 * 订单记录
 */
public class OrdersPojo implements Serializable {


    private int id;
    //用户编号
    private int userId;
    //订单编号
    private String orderCode;
    //房间编号
    private int roomId;
    //花费金额
    private double pay;
    //状态，1待支付,2:预订中,3正常入住,4取消订单,5延长入住时间，6已退房，7已评价
    private int status;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    //对应用户信息
    private UserPojo user;
    //房间
    private RoomPojo room;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public UserPojo getUser() {
        return user;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public RoomPojo getRoom() {
        return room;
    }

    public void setRoom(RoomPojo room) {
        this.room = room;
    }
}
