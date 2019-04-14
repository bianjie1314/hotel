package com.syshotel.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 */
public class RolePojo implements Serializable {

    private int id;
    //名称
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
