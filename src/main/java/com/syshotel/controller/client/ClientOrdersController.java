package com.syshotel.controller.client;


import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.OrderProcessLogPojo;
import com.syshotel.pojo.OrdersPojo;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IOrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
    //根据id获取
    @RequestMapping(value="/getOrderById/{orderId}",method=RequestMethod.GET )
    public String getOrderById(@PathVariable("orderId") int id, String view, Model model){
        logger.info("********** 进入 client - getOrderById 方法,id={}********** ",new Object[]{id});
        model.addAttribute("orderInfo",iOrdersService.getById(id));
        return view;
    }

    //数量统计
    @RequestMapping(value="/countOrderStatus",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult countOrderStatus(HttpServletRequest request){
        logger.info("********** 进入 client - countOrderStatus 方法 ********** ");
        UserPojo user = (UserPojo)request.getSession().getAttribute("clientUserInfo");
        return iOrdersService.countOrderStatus(user);
    }

    //取消订单
    @RequestMapping(value = "/cancerOrder/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult cancerOrder(@PathVariable("id") String choiceId,HttpServletRequest request) {
        logger.info("************  进入  cancerOrder 方法,choiceId={} ************ ",new Object[]{choiceId});
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("clientUserInfo");
        return iOrdersService.cancerOrder(choiceId,userInfo);
    }

    //统计需要支付的金额账单
    @RequestMapping(value="/countPayFree")
    @ResponseBody
    public CommonResult countPayFree(String orderIds,HttpServletRequest request){
        logger.info("********** 进入 client - countPayFree 方法,orderIds={}********** ",new Object[]{orderIds});
        return iOrdersService.countPayFree(orderIds);
    }

    //统计延长住时间需要付的费用
    @RequestMapping(value="/countContinuePay")
    @ResponseBody
    public CommonResult countContinuePay(int roomId,String startTime,String endTime,HttpServletRequest request){
        logger.info("********** 进入 client - countContinuePay 方法,roomId={},startTime={},endTime={}********** ",new Object[]{roomId,startTime,endTime});
        return iOrdersService.countContinuePay(roomId,startTime,endTime);
    }

    //支付订单
    @RequestMapping(value="/payOrders")
    @ResponseBody
    public CommonResult payOrders(String orderIds,HttpServletRequest request){
        logger.info("********** 进入 client - payOrders 方法,orderIds={}********** ",new Object[]{orderIds});
        UserPojo user = (UserPojo)request.getSession().getAttribute("clientUserInfo");
        CommonResult commonResult = iOrdersService.payOrders(orderIds, user);
        if (commonResult.isResult()){
            //更新客户端的信息缓存(余额可能存在修改)
            request.getSession().setAttribute("clientUserInfo",user);
        }
        return commonResult;
    }


    //预定待支付
    @RequestMapping(value="/addCar")
    @ResponseBody
    public CommonResult addCar(Integer roomId, String startTime, String endTime,HttpServletRequest request){
        logger.info("********** 进入 client - addOrders 方法,roomId={},startTime={},endTime={}********** ",new Object[]{roomId,startTime,endTime});
        UserPojo user = (UserPojo)request.getSession().getAttribute("clientUserInfo");
        return iOrdersService.addBean(roomId,startTime,endTime,user);
    }


    //入住延长
    @RequestMapping(value = "/continueOrder")
    @ResponseBody
    public CommonResult continueOrder(@RequestBody OrderProcessLogPojo orderProcessLogPojo, HttpServletRequest request) {
        logger.info("************  进入  client - continueOrder 方法,orderProcessLogPojo={}, ************ ",new Object[]{orderProcessLogPojo});
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("clientUserInfo");
        return iOrdersService.continueOrder(orderProcessLogPojo,userInfo);
    }
}
