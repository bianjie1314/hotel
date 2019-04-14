package com.syshotel.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单
 */
public class MenuPojo implements Serializable {


    private int id;
    //名称
    private String name;
    //链接
    private String url;
    //图标
    private String icon;
    //所属父菜单id
    private int parentId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //状态,1正常,2禁用
    private int status;
    //显示顺序
    private int index;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
