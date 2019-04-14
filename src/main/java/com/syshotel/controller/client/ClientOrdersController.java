package com.syshotel.controller.client;


import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.OrdersPojo;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IOrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单信息接口
 */
@Controller
@RequestMapping(value="/client/order")
public class ClientOrdersController {

    private static Logger logger = LoggerFactory.getLogger(ClientOrdersController.class);

    @Autowired
    private IOrdersService iOrdersService;


    //获取列表
    @RequestMapping(value="/getOrderList",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult getOrderList(SearchVo searchVo, PageBean page,HttpServletRequest request){
        logger.info("********** 进入 client - getOrderList 方法,searchVo={},page={}********** ",new Object[]{searchVo,page});
        UserPojo user = (UserPojo)request.getSession().getAttribute("clientUserInfo");
        return iOrdersService.getOrdersList(searchVo, page,user);
    }

    //删除
    @RequestMapping(value="/delete/{id}",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult deleteOrders(@PathVariable("id") int id){
        logger.info("********** 进入 client - deleteOrders 方法,id={}********** ",new Object[]{id});
        return iOrdersService.deleteById(id);
    }


    //统计需要支付的金额账单
    @RequestMapping(value="/countPayFree")
    @ResponseBody
    public CommonResult countPayFree(String orderIds,HttpServletRequest request){
        logger.info("********** 进入 client - countPayFree 方法,orderIds={}********** ",new Object[]{orderIds});
        return iOrdersService.countPayFree(orderIds);
    }

    //支付订单
    @RequestMapping(value="/payOrders")
    @ResponseBody
    public CommonResult payOrders(String orderIds,HttpServletRequest request){
        logger.info("********** 进入 client - payOrders 方法,orderIds={}********** ",new Object[]{orderIds});
        UserPojo user = (UserPojo)request.getSession().getAttribute("clientUserInfo");
        return iOrdersService.payOrders(orderIds,user);
    }

}
