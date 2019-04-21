package com.syshotel.controller;


import com.syshotel.service.IOrderProcessLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 订单流水记录
 */
@Controller
@RequestMapping(value="/orderLog")
public class OrderProcessLogController {

    private static Logger logger = LoggerFactory.getLogger(OrderProcessLogController.class);

    @Autowired
    IOrderProcessLogService iOrderProcessLogService;

    //通过单号获取流水记录
    @RequestMapping(value = "/getOrderProcess/{orderId}", method = RequestMethod.GET)
    public String getOrderProcess(@PathVariable("orderId") int orderId,Model model) {
        logger.info("************  进入  orderProcess 方法,choiceId={} ************ ",new Object[]{orderId});
        model.addAttribute("logList", iOrderProcessLogService.selectByOrderId(orderId));
        return "admin/order/order-log";
    }

}
