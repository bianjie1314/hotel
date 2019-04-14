package com.syshotel.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单流程操作记录
 */
public class OrderProcessLogPojo implements Serializable {

    private int id;
    //订单编号id
    private int orderId;
    //操作人
    private int doUserId;
    //状态，1:预订中,2正常入住,3取消订单,4延长入住时间，5退房成功，6已评价
    private int status;
    //备注
    private String note;
    //创建时间
    private Date createTime;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDoUserId() {
        return doUserId;
    }

    public void setDoUserId(int doUserId) {
        this.doUserId = doUserId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
