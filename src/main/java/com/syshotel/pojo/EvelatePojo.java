package com.syshotel.pojo;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评价信息
 */
public class EvelatePojo extends OrdersPojo implements Serializable {


    private int id;
    //评价内容
    private String content;
    //照片信息
    private List<PicturePojo> pictureList;
    //订单主键
    private int orderId;
    //审核状态,1审核通过，2审核拒绝
    private int status;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<PicturePojo> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<PicturePojo> pictureList) {
        this.pictureList = pictureList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
