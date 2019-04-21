package com.syshotel.service;


import com.syshotel.pojo.OrderProcessLogPojo;

import java.util.List;

public interface IOrderProcessLogService {

    /**
     * 获取订单对应的流水记录
     * @param orderId
     * @return
     */
    List<OrderProcessLogPojo> selectByOrderId(int orderId);
}
