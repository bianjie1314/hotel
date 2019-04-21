package com.syshotel.service;

import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.OrderProcessLogPojo;
import com.syshotel.pojo.OrdersPojo;
import com.syshotel.pojo.UserPojo;

import java.util.Date;
import java.util.List;

public interface IOrdersService {

    /**
     * 添加信息
     * @param roomId
     * @param startTime
     * @param endTime
     * @param user
     * @return
     */
    public CommonResult addBean(Integer roomId, String startTime, String endTime, UserPojo user);

    /**
     * 统计各种订单的数量
     * @param user
     * @return
     */
    public CommonResult countOrderStatus(UserPojo user);

    /**
     * 通过id删除
     * @param id
     */
    public CommonResult deleteById(int id);

    /**
     * 更新
     * @param ordersPojo
     */
    public CommonResult updateBean(OrdersPojo ordersPojo);

    /**
     * 查询满足条件的信息
     * @param searchVo
     * @param page
     * @param userInfo
     * @return
     */
    public List<OrdersPojo> selectOrdersList(SearchVo searchVo, PageBean page,UserPojo userInfo);

    /**
     * 查询满足条件的信息
     * @param searchVo
     * @param page
     * @param user
     * @return
     */
    public CommonResult getOrdersList(SearchVo searchVo, PageBean page, UserPojo user);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public OrdersPojo getById(int id);

    /**
     * 更新状态
     * @param id
     * @param status
     * @return
     */
    public CommonResult updateStatus(int id, int status);

    /**
     * 更新订单数量
     * @param orders
     * @return
     */
    public CommonResult updateOrderNum(OrdersPojo orders);

    /**
     * 订单支付
     * @param orderIds : 订单号用","拼接
     * @param user
     * @return
     */
    public CommonResult payOrders(String orderIds, UserPojo user);

    /**
     * 统计需要支付的账单费用
     * @param orderIds
     * @return
     */
    public CommonResult countPayFree(String orderIds);

    /**
     * 用户已入住
     * @param choiceId
     * @param userInfo
     * @return
     */
    public CommonResult userEnterOrder(String choiceId, UserPojo userInfo);

    /**
     * 取消订单
     * @param choiceId
     * @param userInfo
     * @return
     */
    public CommonResult cancerOrder(String choiceId, UserPojo userInfo);

    /**
     * 退房操作
     * @param choiceId
     * @param userInfo
     * @return
     */
    public CommonResult finishOrder(String choiceId, UserPojo userInfo);
    /**
     * 延长入住
     * @param orderProcessLogPojo
     * @param userInfo
     * @return
     */
    public CommonResult continueOrder(OrderProcessLogPojo orderProcessLogPojo, UserPojo userInfo);

    /**
     * 计算延长入住时间需要付的费用
     * @param roomId
     * @param startTime
     * @param endTime
     * @return
     */
    public CommonResult countContinuePay(int roomId, String startTime, String endTime);
}
