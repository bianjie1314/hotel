package com.syshotel.common;

/**
 * 分页
 */
public class PageBean {

    //开始
    private int start = -1;
    //数量
    private int offset;
    //总数
    private int total;
    //当前
    private int current;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
