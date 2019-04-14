package com.syshotel.controller;


import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IOrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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


    //发货
    @RequestMapping(value = "/delieveryOrder", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delieveryOrder(String choiceId,HttpServletRequest request) {
        logger.info("************  进入  delieveryOrder 方法,choiceId={} ************ ",new Object[]{choiceId});
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        return iOrdersService.delieveryOrder(choiceId,userInfo);
    }


    //退货
    @RequestMapping(value = "/returnOrder", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult returnOrder(String choiceId,int status,HttpServletRequest request) {
        logger.info("************  进入  returnOrder 方法,choiceId={},status={} ************ ",new Object[]{choiceId,status});
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        return iOrdersService.returnOrder(choiceId,status,userInfo);
    }



    //删除
    @RequestMapping(value="/delete",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult deleteOrders(int id){
        logger.info("********** 进入 deleteOrders 方法,id={}********** ",new Object[]{id});
        return iOrdersService.deleteById(id);
    }

}
