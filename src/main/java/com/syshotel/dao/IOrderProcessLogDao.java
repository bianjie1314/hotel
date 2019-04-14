package com.syshotel.dao;

import com.syshotel.pojo.OrderProcessLogPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderProcessLogDao {

    /**
     * 添加信息
     * @param processLogPojo
     * @return
     */
    public int addBean(OrderProcessLogPojo processLogPojo);

    /**
     * 批量插入
     * @param beans
     * @return
     */
    public void insertBatch(@Param("beans") List<OrderProcessLogPojo> beans);


    /**
     * 通过订单编号id获取关联操作日志
     * @param orderId
     * @return
     */
    public List<OrderProcessLogPojo> selectByOrderId(int orderId);
}
