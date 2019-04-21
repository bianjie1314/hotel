package com.syshotel.service.impl;


import com.syshotel.dao.IOrderProcessLogDao;
import com.syshotel.pojo.OrderProcessLogPojo;
import com.syshotel.service.IOrderProcessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrderProcessLogServiceImpl implements IOrderProcessLogService {

    @Autowired
    IOrderProcessLogDao iOrderProcessLogDao;

    @Override
    public List<OrderProcessLogPojo> selectByOrderId(int orderId) {
        if (StringUtils.isEmpty(orderId)){
            return null;
        }
        return iOrderProcessLogDao.selectByOrderId(orderId);
    }
}
