package com.syshotel.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 钱包操作记录
 */
public class WalletLogPojo implements Serializable {

    private int id;
    //用户编号
    private int userId;
    //订单主键
    private int orderId;
    //金额
    private double money;
    //类型,1充值,2付款,3退款
    private int type;
    //备注
    private String note;
    //创建时间
    private Date createTime;


    //一对一,用户实体
    private UserPojo user;
    //订单实体
    private OrdersPojo order;

    /** 以下字段用于显示 **/
    //用户名
    private String username;
    //订单编号
    private String orderCode;

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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UserPojo getUser() {
        return user;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }

    public OrdersPojo getOrder() {
        return order;
    }

    public void setOrder(OrdersPojo order) {
        this.order = order;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
