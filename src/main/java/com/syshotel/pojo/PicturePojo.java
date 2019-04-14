package com.syshotel.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 照片
 */
public class PicturePojo implements Serializable {

    private int id;
    //存储路径
    private String pathUrl;
    //类型,1:手机照片信息,2:用户评价照片
    private int type;
    //创建时间
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
