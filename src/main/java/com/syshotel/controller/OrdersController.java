package com.syshotel.controller;


import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.OrderProcessLogPojo;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IOrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单信息接口
 */
@Controller
@RequestMapping(value="/order")
public class OrdersController {

    private static Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    private IOrdersService iOrdersService;


    //获取列表
    @RequestMapping(value="/getOrderList",method=RequestMethod.GET )
    public String getOrderList(SearchVo searchVo, PageBean page, Model model, HttpServletRequest request){
        logger.info("********** 进入 getOrderList 方法,searchVo={},page={}********** ",new Object[]{searchVo,page});
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        model.addAttribute("orderList", iOrdersService.selectOrdersList(searchVo,page,userInfo));
        model.addAttribute("searchVo", searchVo);
        return "admin/order/order-list";
    }

    //根据id获取
    @RequestMapping(value="/getOrderById/{orderId}",method=RequestMethod.GET )
    public String getOrderById(@PathVariable("orderId") int id, String view, Model model){
        logger.info("********** 进入 getOrderById 方法,id={}********** ",new Object[]{id});
        model.addAttribute("orderInfo",iOrdersService.getById(id));
        return view;
    }

    //入住
    @RequestMapping(value = "/userEnterOrder", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult userEnterOrder(String choiceId,HttpServletRequest request) {
        logger.info("************  进入  userEnterOrder 方法,choiceId={} ************ ",new Object[]{choiceId});
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        return iOrdersService.userEnterOrder(choiceId,userInfo);
    }


    //取消订单
    @RequestMapping(value = "/cancerOrder", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult cancerOrder(String choiceId,HttpServletRequest request) {
        logger.info("************  进入  cancerOrder 方法,choiceId={} ************ ",new Object[]{choiceId});
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        return iOrdersService.cancerOrder(choiceId,userInfo);
    }

    //退房确认
    @RequestMapping(value = "/finishOrder", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult finishOrder(String choiceId,HttpServletRequest request) {
        logger.info("************  进入  finishOrder 方法,choiceId={}, ************ ",new Object[]{choiceId});
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        return iOrdersService.finishOrder(choiceId,userInfo);
    }


    //删除
    @RequestMapping(value="/delete",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult deleteOrders(int id){
        logger.info("********** 进入 deleteOrders 方法,id={}********** ",new Object[]{id});
        return iOrdersService.deleteById(id);
    }

}
