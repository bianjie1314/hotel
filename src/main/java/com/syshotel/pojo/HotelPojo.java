package com.syshotel.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 酒店
 */
public class HotelPojo implements Serializable {

    private int id;
    //名称
    private String name;
    //店主
    private int userId;
    //状态,1正常，2下线
    private int status;
    //地理位置
    private String location;
    //照片id
    private String pictureIds;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //营业时间
    private String openTime;
    //照片
    private List<PicturePojo> pictures;
    //所属用户
    private UserPojo user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserPojo getUser() {
        return user;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }
}
