package com.syshotel.service.impl;

import com.syshotel.common.*;
import com.syshotel.dao.IOrderProcessLogDao;
import com.syshotel.dao.IOrdersDao;
import com.syshotel.pojo.OrderProcessLogPojo;
import com.syshotel.pojo.OrdersPojo;
import com.syshotel.pojo.UserPojo;
import com.syshotel.pojo.vo.OrderFreeVo;
import com.syshotel.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    IOrdersDao iOrdersDao;
    @Autowired
    IOrderProcessLogDao orderProcessLogDao;

    @Override
    public CommonResult addBean(OrdersPojo ordersPojo) {
        int result = iOrdersDao.addBean(ordersPojo);
        if (result <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        return CommonResult.SUCCESS(MessageConstant.ADD_SUCCESS,result);
    }

    @Override
    public CommonResult deleteById(int id) {
        if (id <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        iOrdersDao.deleteById(id);
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    @Override
    public CommonResult updateBean(OrdersPojo ordersPojo) {
        if (ordersPojo == null || ordersPojo.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        iOrdersDao.updateBean(ordersPojo);
        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,null);

    }

    @Override
    public List<OrdersPojo> selectOrdersList(SearchVo searchVo, PageBean page,UserPojo userInfo) {
        if (userInfo == null){
            return null;
        }
        return iOrdersDao.selectOrdersDetailList(getQueryMap(searchVo,page,userInfo));
    }
    private Map<String,Object> getQueryMap(SearchVo searchVo, PageBean page,UserPojo userInfo){
        Map<String,Object> paramMap = new HashMap<>();
        if (userInfo != null && userInfo.getRoleId() != Constant.USER_ADMIN){//非管理员
            if ( userInfo.getRoleId() == Constant.USER_SHOP){
                paramMap.put("hashId",userInfo.getId());//店家,酒店拥有者
            }else {
                paramMap.put("userId",userInfo.getId());//普通用户，下单者
            }
        }
        if (searchVo != null){
            if (!StringUtils.isEmpty(searchVo.getText())){
                paramMap.put("orderCode",searchVo.getText());
            }
            if (searchVo.getIndex() > 0){
                paramMap.put("status",searchVo.getIndex());
            }
            if (searchVo.getUserFlag() > 0){
                paramMap.put("userId",searchVo.getUserFlag());
            }
            if (!StringUtils.isEmpty(searchVo.getStartTime())){
                paramMap.put("startTime",searchVo.getStartTime());
            }
            if (!StringUtils.isEmpty(searchVo.getEndTime())){
                paramMap.put("endTime",searchVo.getEndTime());
            }
            if (!StringUtils.isEmpty(searchVo.getCategory())){
                paramMap.put("status",searchVo.getCategory());
            }
        }
        if (page != null && page.getStart() >= 0){
            paramMap.put("start",page.getStart());
            paramMap.put("offset",page.getOffset());
        }
        return paramMap;
    }

    @Override
    public CommonResult getOrdersList(SearchVo searchVo, PageBean page, UserPojo user) {
        if (user == null){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        Map<String, Object> queryMap = getQueryMap(searchVo, page,user);
        int count = iOrdersDao.countOrders(queryMap);
        if (count <= 0){
            return CommonResult.SUCCESS(0,null);
        }
        page.setTotal(count);
        return CommonResult.SUCCESS(count,iOrdersDao.selectOrdersDetailList(queryMap));
    }


    @Override
    public OrdersPojo getById(int id) {
        return iOrdersDao.getById(id);
    }

    @Override
    public CommonResult updateStatus(int id, int status) {
        if (id <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        OrdersPojo bean = iOrdersDao.getById(id);
        if (bean == null){
            return CommonResult.ERROR(MessageConstant.ORDER_NO_EXISTS);
        }
        return CommonResult.ERROR(MessageConstant.UPDATE_SUCCESS);
    }

    @Override
    public CommonResult delieveryOrder(String idStr,UserPojo userPojo) {
        if (StringUtils.isEmpty(idStr)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }

        String[] ids = (idStr.substring(idStr.indexOf(',') + 1)).split(",");

        Map<String,Object> map = new HashMap<>();
        map.put("ids",ids);
        List<OrdersPojo> ordersPojos = iOrdersDao.selectOrdersList(map);
        if (ordersPojos == null || ordersPojos.size() == 0){
            return CommonResult.ERROR(MessageConstant.ORDER_NO_EXISTS);
        }

        List<OrderProcessLogPojo> processLogPojos = new ArrayList<>();
        for (OrdersPojo order:ordersPojos) {
            if (order.getStatus()== Constant.ORDER_PAY_DOING){//未支付
                return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_NO_PAY);
            }

            if (order.getStatus() == Constant.ORDER_CANCER){//已取消
                return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_HAVE_CANCER);
            }

            if (order.getStatus() >= Constant.ORDER_GAINS){//已发货
                return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_HAVE_DELIVERY);
            }

            OrderProcessLogPojo processLogPojo = new OrderProcessLogPojo();
            processLogPojo.setCreateTime(new Date());
            processLogPojo.setOrderId(order.getId());
            processLogPojo.setStatus(Constant.ORDER_GAINS);
            processLogPojo.setDoUserId(userPojo.getId());
            processLogPojos.add(processLogPojo);
        }
        //设置待收货状态
        map.put("status",Constant.ORDER_GAINS);
        map.put("updateTime",new Date());
        iOrdersDao.updateByMap(map);

        //发货记录
        if (processLogPojos.size() > 0){
            orderProcessLogDao.insertBatch(processLogPojos);
        }

        return CommonResult.SUCCESS(MessageConstant.ORDER_DELIVERY_SUCCESS,null);
    }

    @Override
    public CommonResult returnOrder(String idStr,int status, UserPojo userPojo) {
        if (StringUtils.isEmpty(idStr) || status < Constant.ORDER_RETURN_APPLAY || status > Constant.ORDER_RETURN_REFUSE){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }

        String[] ids = (idStr.substring(idStr.indexOf(',') + 1)).split(",");

        Map<String,Object> map = new HashMap<>();
        map.put("ids",ids);
        List<OrdersPojo> ordersPojos = iOrdersDao.selectOrdersList(map);
        if (ordersPojos == null || ordersPojos.size() == 0){
            return CommonResult.ERROR(MessageConstant.ORDER_NO_EXISTS);
        }

        List<OrderProcessLogPojo> processLogPojos = new ArrayList<>();
        for (OrdersPojo order:ordersPojos) {

            if (order.getStatus() == Constant.ORDER_CANCER){//已取消
                return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_HAVE_CANCER);
            }

            if (order.getStatus() != Constant.ORDER_RETURN_APPLAY){//已处理过退货申请
                return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_NO_RETURN_APPLAY);
            }

            OrderProcessLogPojo processLogPojo = new OrderProcessLogPojo();
            processLogPojo.setCreateTime(new Date());
            processLogPojo.setOrderId(order.getId());
            processLogPojo.setStatus(status);
            processLogPojo.setDoUserId(userPojo.getId());
            processLogPojos.add(processLogPojo);
        }
        //设置待收货状态
        map.put("status",status);
        map.put("updateTime",new Date());
        iOrdersDao.updateByMap(map);

        //发货记录
        if (processLogPojos.size() > 0){
            orderProcessLogDao.insertBatch(processLogPojos);
        }

        return CommonResult.SUCCESS(MessageConstant.ORDER_RETURN_APPLAY_SOLVE_SUCCESS,null);
    }

    @Override
    public CommonResult updateOrderNum(OrdersPojo orders) {
        if (orders == null || orders.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        iOrdersDao.updateBean(orders);
        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,null);
    }

    @Override
    public CommonResult payOrders(String orderIds,UserPojo user) {
        if (StringUtils.isEmpty(orderIds)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        String[] orderIdArr = orderIds.split(",");

        List<OrderProcessLogPojo> processLogPojoList = new ArrayList<>();
        for (String orderIdStr:orderIdArr){
            int orderId = Integer.parseInt(orderIdStr);
            //更新订单状态为已支付(待发货)
            OrdersPojo ordersPojo = new OrdersPojo();
            ordersPojo.setId(orderId);
            ordersPojo.setStatus(Constant.ORDER_DELIVERY);
            ordersPojo.setUpdateTime(new Date());
            iOrdersDao.updateBean(ordersPojo);

            //日志记录
            OrderProcessLogPojo processLogPojo = new OrderProcessLogPojo();
            processLogPojo.setStatus(Constant.ORDER_DELIVERY);
            processLogPojo.setDoUserId(user.getId());
            processLogPojo.setCreateTime(new Date());
            processLogPojo.setOrderId(orderId);
            processLogPojoList.add(processLogPojo);
        }
        //批量插入订单操作日志
        orderProcessLogDao.insertBatch(processLogPojoList);
        return CommonResult.SUCCESS(MessageConstant.PAY_SUCCESS,null);
    }

    @Override
    public CommonResult countPayFree(String orderIds) {

        OrderFreeVo orderFreeVo = new OrderFreeVo();
        if (!StringUtils.isEmpty(orderIds)){
            String[] orderIdArr = orderIds.split(",");
            orderFreeVo.setSize(orderIdArr.length);
            //费用明细
            for (String orderIdStr:orderIdArr){
                int orderId = Integer.parseInt(orderIdStr);
                //更新订单状态为已支付(待发货)
                OrdersPojo ordersPojo = iOrdersDao.getById(orderId);
                //由于没有设置快递费用,此处将需要支付的金额与商品总价一样
                orderFreeVo.setPhonePrice(orderFreeVo.getPhonePrice()+ordersPojo.getPay());
                orderFreeVo.setTotalPrice(orderFreeVo.getTotalPrice()+ordersPojo.getPay());
            }
        }

        return CommonResult.SUCCESS(orderFreeVo);
    }
}
