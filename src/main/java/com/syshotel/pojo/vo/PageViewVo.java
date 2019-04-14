package com.syshotel.pojo.vo;

import java.io.Serializable;

public class PageViewVo implements Serializable {

    //大小
    private int size;
    //数据
    private Object data;

    public PageViewVo(int size, Object data) {
        this.size = size;
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
