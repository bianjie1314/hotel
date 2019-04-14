package com.syshotel.pojo.vo;

import java.io.Serializable;

/**
 * 订单支付的费用统计
 */
public class OrderFreeVo implements Serializable {


    private int size;   //单数

    private double phonePrice;//商品总价

    private double deliveryPrice;//快递费用

    private double totalPrice;//最终需要支付的费用

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPhonePrice() {
        return phonePrice;
    }

    public void setPhonePrice(double phonePrice) {
        this.phonePrice = phonePrice;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
