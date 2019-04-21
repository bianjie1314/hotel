package com.syshotel.service.impl;

import com.syshotel.common.*;
import com.syshotel.dao.IOrderProcessLogDao;
import com.syshotel.dao.IOrdersDao;
import com.syshotel.dao.IPictureDao;
import com.syshotel.dao.IWalletLogDao;
import com.syshotel.pojo.*;
import com.syshotel.pojo.vo.OrderFreeVo;
import com.syshotel.service.IOrdersService;
import com.syshotel.service.IRoomService;
import com.syshotel.service.IUserService;
import com.syshotel.utils.DateUtil;
import com.syshotel.utils.NumberUtil;
import com.syshotel.utils.OrderCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    IOrdersDao iOrdersDao;
    @Autowired
    IOrderProcessLogDao orderProcessLogDao;
    @Autowired
    IWalletLogDao iWalletLogDao;
    @Autowired
    IUserService iUserService;
    @Autowired
    IRoomService iRoomService;
    @Autowired
    IPictureDao iPictureDao;

    @Transactional
    @Override
    public CommonResult addBean(Integer roomId, String startTime, String endTime, UserPojo user){
        if (user == null || user.getId() <= 0 ){
            return CommonResult.ERROR(MessageConstant.NEED_LOGIN);
        }
        if (roomId == null || startTime == null || endTime == null){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }

        RoomPojo room = iRoomService.getById(roomId);
        if (room == null){
            return CommonResult.ERROR(MessageConstant.ROOM_NO_EXISTS);
        }

        Date dateStart = DateUtil.parse2Date(startTime, "yyyy-MM-dd");
        Date dateEnd = DateUtil.parse2Date(endTime, "yyyy-MM-dd");
        //未支付
        OrdersPojo ordersPojo = new OrdersPojo();
        ordersPojo.setRoomId(roomId);
        ordersPojo.setStatus(Constant.ORDER_PAY_DOING);
        ordersPojo.setCreateTime(new Date());
        ordersPojo.setUserId(user.getId());
        //计算金额,每天房租*天数
        long[] distanceTime = DateUtil.getDistanceTime(dateEnd,dateStart);
        ordersPojo.setPay(room.getMoney() * (distanceTime[0] == 0?1:distanceTime[0]));
        //订单编号
        ordersPojo.setOrderCode(OrderCodeUtil.getOrderCode());
        int result = iOrdersDao.addBean(ordersPojo);
        if (result <= 0){
            return CommonResult.ERROR(MessageConstant.RUN_ERROR);
        }
        //订单记录
        OrderProcessLogPojo processLogPojo = new OrderProcessLogPojo();
        processLogPojo.setStartTime(dateStart);
        processLogPojo.setEndTime(dateEnd);
        processLogPojo.setCreateTime(new Date());
        processLogPojo.setOrderId(result);
        processLogPojo.setStatus(Constant.ORDER_PAY_DOING);
        processLogPojo.setDoUserId(ordersPojo.getId());
        orderProcessLogDao.addBean(processLogPojo);

        return CommonResult.SUCCESS(MessageConstant.ADD_SUCCESS,result);
    }

    @Override
    public CommonResult countOrderStatus(UserPojo user) {
        if(user == null || user.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        //统计各种订单的数量
        Map<String,Integer> map = iOrdersDao.countOrderStatus(user.getId());
        return CommonResult.SUCCESS(map);
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
            if (!StringUtils.isEmpty(searchVo.getCategory())){
                List<Integer> listIds = Arrays.asList(searchVo.getCategory().split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
                paramMap.put("statusRange",listIds);
            }
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
        List<OrdersPojo> ordersPojos = iOrdersDao.selectOrdersDetailList(queryMap);
        if (ordersPojos != null && ordersPojos.size() > 0) {
            for (int i = 0; i < ordersPojos.size(); i++) {
                OrdersPojo ordersPojo = ordersPojos.get(i);
                if (ordersPojo.getRoom() != null && ordersPojo.getRoom().getPictureIds() != null) {
                    List<PicturePojo> byIds = iPictureDao.getByIds(Arrays.asList(ordersPojo.getRoom().getPictureIds().split(",")));
                    ordersPojo.getRoom().setPictures(byIds);
                }
            }
        }
        return CommonResult.SUCCESS(count,ordersPojos);
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
    public CommonResult userEnterOrder(String choiceId, UserPojo userInfo) {
        if (StringUtils.isEmpty(choiceId)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }

        String[] ids = (choiceId.substring(choiceId.indexOf(',') + 1)).split(",");

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

            if (order.getStatus() != Constant.ORDER_IN_PRE){//不是待入住
                return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_NO_WAIT_USE);
            }
            OrderProcessLogPojo processLogPojo = new OrderProcessLogPojo();
            processLogPojo.setCreateTime(new Date());
            processLogPojo.setOrderId(order.getId());
            processLogPojo.setStatus(Constant.ORDER_IN_USE);
            processLogPojo.setDoUserId(userInfo.getId());
            processLogPojos.add(processLogPojo);
        }
        //设置待收货状态
        map.put("status",Constant.ORDER_IN_USE);
        map.put("updateTime",new Date());
        iOrdersDao.updateByMap(map);

        //发货记录
        if (processLogPojos.size() > 0){
            orderProcessLogDao.insertBatch(processLogPojos);
        }

        return CommonResult.SUCCESS(MessageConstant.ORDER_INUSE_SUCCESS,null);
    }


    @Transactional
    @Override
    public CommonResult cancerOrder(String ids,  UserPojo userInfo) {
        if (StringUtils.isEmpty(ids)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }

        String[] idArr = (ids.substring(ids.indexOf(',') + 1)).split(",");

        Map<String,Object> map = new HashMap<>();
        map.put("ids",idArr);
        List<OrdersPojo> ordersPojos = iOrdersDao.selectOrdersList(map);
        if (ordersPojos == null || ordersPojos.size() == 0){
            return CommonResult.ERROR(MessageConstant.ORDER_NO_EXISTS);
        }

        List<OrderProcessLogPojo> processLogPojos = new ArrayList<>();
        List<UserPojo> userPojos = new ArrayList<>();
        List<WalletLogPojo> walletLogPojos = new ArrayList<>();
        for (OrdersPojo order:ordersPojos) {

            if (order.getStatus() == Constant.ORDER_CANCER){//已取消
                return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_HAVE_CANCER);
            }

            if (order.getStatus() > Constant.ORDER_IN_PRE){//已发货无法取消
                return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_NO_PAY_OR_WAIT_USE);
            }

            OrderProcessLogPojo processLogPojo = new OrderProcessLogPojo();
            processLogPojo.setCreateTime(new Date());
            processLogPojo.setOrderId(order.getId());
            processLogPojo.setStatus(Constant.ORDER_CANCER);
            processLogPojo.setDoUserId(userInfo.getId());
            processLogPojos.add(processLogPojo);

            //已支付进行金额退款
            if (order.getStatus() == Constant.ORDER_IN_PRE){
                //用户账户
                UserPojo userPojo = new UserPojo();
                userPojo.setId(order.getUserId());
                userPojo.setBalance(order.getPay());
                userPojos.add(userPojo);
                //退款记录
                WalletLogPojo walletLogPojo = new WalletLogPojo();
                walletLogPojo.setCreateTime(new Date());
                walletLogPojo.setNote("订单取消退款");
                walletLogPojo.setType(Constant.WALLET_CANCER_3);
                walletLogPojo.setUserId(order.getUserId());
                walletLogPojo.setMoney(order.getPay());
                walletLogPojo.setOrderId(order.getId());
                walletLogPojos.add(walletLogPojo);
            }
        }
        //设置待收货状态
        map.put("status",Constant.ORDER_CANCER);
        map.put("updateTime",new Date());
        iOrdersDao.updateByMap(map);

        //用户金额退款
        if (userPojos.size() > 0){
            iUserService.batchUpdateMoney(userPojos);
        }

        //财务记录
        if (walletLogPojos.size() > 0){
            iWalletLogDao.insertBatch(walletLogPojos);
        }

        //记录
        if (processLogPojos.size() > 0){
            orderProcessLogDao.insertBatch(processLogPojos);
        }

        return CommonResult.SUCCESS(MessageConstant.ORDER_CANCER_SUCCESS,null);
    }

    @Transactional
    @Override
    public CommonResult finishOrder(String idStr,UserPojo userPojo) {
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
        List<Integer> roomIds = new ArrayList<>();
        for (OrdersPojo order:ordersPojos) {

            if (order.getStatus() == Constant.ORDER_CANCER){//已取消
                return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_HAVE_CANCER);
            }
            //入住状态
            if (order.getStatus() != Constant.ORDER_IN_USE && order.getStatus() != Constant.ORDER_CONTINUE_USE){
                return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_NO_IN_USE);
            }

            OrderProcessLogPojo processLogPojo = new OrderProcessLogPojo();
            processLogPojo.setCreateTime(new Date());
            processLogPojo.setOrderId(order.getId());
            processLogPojo.setStatus(Constant.ORDER_FINISH);
            processLogPojo.setDoUserId(userPojo.getId());
            processLogPojos.add(processLogPojo);


            //房间id
            roomIds.add(order.getRoomId());

        }
        //设置待收货状态
        map.put("status",Constant.ORDER_FINISH);
        map.put("updateTime",new Date());
        iOrdersDao.updateByMap(map);

        //更新房间,设置为可用
        map.put("status",Constant.ROOM_STATUS_NORMAL);
        map.put("ids",roomIds);
        iRoomService.updateByMap(map);

        //记录
        if (processLogPojos.size() > 0){
            orderProcessLogDao.insertBatch(processLogPojos);
        }

        return CommonResult.SUCCESS(MessageConstant.ORDER_FINISH,null);
    }

    @Transactional
    @Override
    public CommonResult continueOrder(OrderProcessLogPojo orderProcessLogPojo, UserPojo userPojo) {
        if (orderProcessLogPojo == null || userPojo == null || userPojo.getId()<=0 || orderProcessLogPojo.getOrderId() <= 0
                ||orderProcessLogPojo.getStartTime() == null || orderProcessLogPojo.getEndTime() == null){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }

        OrdersPojo order = iOrdersDao.getById(orderProcessLogPojo.getOrderId());
        if (order == null){
            return CommonResult.ERROR(MessageConstant.ORDER_NO_EXISTS);
        }

        if (order.getStatus() == Constant.ORDER_CANCER){//已取消
            return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_HAVE_CANCER);
        }
        //入住状态
        if (order.getStatus() != Constant.ORDER_IN_USE && order.getStatus() != Constant.ORDER_CONTINUE_USE){
            return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_NO_IN_USE);
        }

        RoomPojo roomPojo = iRoomService.getById(order.getRoomId());
        if (roomPojo == null){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }

        //计算金额,每天房租*天数
        long[] distanceTime = DateUtil.getDistanceTime(orderProcessLogPojo.getEndTime(),orderProcessLogPojo.getStartTime());
        double needPay = NumberUtil.formateDoubleWithTwoPoint(roomPojo.getMoney() * (distanceTime[0] == 0 ? 1 : distanceTime[0]));
        UserPojo user = iUserService.getById(userPojo.getId());
        if (user.getBalance() < needPay){
            return CommonResult.ERROR(MessageConstant.BALANCE_NO_ENOUGH);
        }

        //更新订单
        OrdersPojo updateOrder = new OrdersPojo();
        updateOrder.setId(order.getId());
        updateOrder.setStatus(Constant.ORDER_CONTINUE_USE);
        updateOrder.setUpdateTime(new Date());
        updateOrder.setPay(needPay+order.getPay());
        iOrdersDao.updateBean(updateOrder);

        //订单记录
        orderProcessLogPojo.setCreateTime(new Date());
        orderProcessLogPojo.setOrderId(order.getId());
        orderProcessLogPojo.setStatus(Constant.ORDER_CONTINUE_USE);
        orderProcessLogPojo.setDoUserId(user.getId());
        orderProcessLogDao.addBean(orderProcessLogPojo);


        //支付记录
        WalletLogPojo walletLogPojo = new WalletLogPojo();
        walletLogPojo.setOrderId(order.getId());
        walletLogPojo.setType(Constant.WALLET_PAY_2);
        walletLogPojo.setNote("订单延长入住支付");
        walletLogPojo.setMoney(needPay);
        walletLogPojo.setUserId(user.getId());
        walletLogPojo.setCreateTime(new Date());
        iWalletLogDao.addBean(walletLogPojo);

        //余额更新
        double balance = NumberUtil.formateDoubleWithTwoPoint(user.getBalance() - needPay);
        UserPojo updateUser = new UserPojo();
        updateUser.setId(user.getId());
        updateUser.setUpdateTime(new Date());
        //减去
        updateUser.setBalance(-needPay);
        iUserService.updateMoney(updateUser);

        return CommonResult.SUCCESS(MessageConstant.ORDER_CONTINUE_SUCCESS,null);
    }


    @Override
    public CommonResult updateOrderNum(OrdersPojo orders) {
        if (orders == null || orders.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        iOrdersDao.updateBean(orders);
        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,null);
    }

    @Transactional
    @Override
    public CommonResult payOrders(String orderIds,UserPojo user) {
        if (StringUtils.isEmpty(orderIds)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        String[] idArr = orderIds.split(",");
        Map<String,Object> map = new HashMap<>();
        map.put("ids",idArr);
        List<OrdersPojo> ordersPojos = iOrdersDao.selectOrdersList(map);
        if (ordersPojos == null || ordersPojos.size() == 0){
            return CommonResult.ERROR(MessageConstant.ORDER_NO_EXISTS);
        }

        List<OrderProcessLogPojo> processLogPojoList = new ArrayList<>();
        List<WalletLogPojo> walletLogPojos = new ArrayList<>();
        //账户余额比较
        double needPay = iOrdersDao.countNeedPay(map);
        UserPojo userPojo = iUserService.getById(user.getId());
        if (userPojo.getBalance() < needPay){
            return CommonResult.ERROR(MessageConstant.BALANCE_NO_ENOUGH);
        }

        for (OrdersPojo order:ordersPojos) {

            if (order.getStatus() == Constant.ORDER_CANCER){//已取消
                return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_HAVE_CANCER);
            }

            if (order.getStatus() != Constant.ORDER_PAY_DOING){//非待支付订单
                return CommonResult.ERROR(order.getOrderCode()+MessageConstant.ORDER_NO_IN_PRE);
            }

            //更新订单状态为已支付(待入住)
            OrdersPojo ordersPojo = new OrdersPojo();
            ordersPojo.setId(order.getId());
            ordersPojo.setStatus(Constant.ORDER_IN_PRE);
            ordersPojo.setUpdateTime(new Date());
            iOrdersDao.updateBean(ordersPojo);

            //日志记录
            OrderProcessLogPojo processLogPojo = new OrderProcessLogPojo();
            processLogPojo.setStatus(Constant.ORDER_IN_PRE);
            processLogPojo.setDoUserId(user.getId());
            processLogPojo.setCreateTime(new Date());
            processLogPojo.setOrderId(order.getId());
            processLogPojoList.add(processLogPojo);


            //钱包付款记录
            WalletLogPojo walletLogPojo = new WalletLogPojo();
            walletLogPojo.setCreateTime(new Date());
            walletLogPojo.setNote("订单支付");
            walletLogPojo.setType(Constant.WALLET_PAY_2);
            walletLogPojo.setUserId(user.getId());
            walletLogPojo.setMoney(order.getPay());
            walletLogPojo.setOrderId(order.getId());
            walletLogPojos.add(walletLogPojo);
        }

        //更新账户余额,保留两个小数点
        double balance = NumberUtil.formateDoubleWithTwoPoint(userPojo.getBalance() - needPay);
        UserPojo updateUser = new UserPojo();
        updateUser.setId(user.getId());
        updateUser.setBalance( - needPay);
        updateUser.setUpdateTime(new Date());
        iUserService.updateMoney(updateUser);
        //更新客户端显示的账户余额问题
        user.setBalance(balance);

        //财务记录
        if (walletLogPojos.size() > 0){
            iWalletLogDao.insertBatch(walletLogPojos);
        }

        if (processLogPojoList.size() > 0){
            orderProcessLogDao.insertBatch(processLogPojoList);
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

    @Override
    public CommonResult countContinuePay(int roomId, String startTime, String endTime) {
        if (roomId <= 0 || StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        RoomPojo roomPojo = iRoomService.getById(roomId);
        if (roomPojo == null){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        OrderFreeVo orderFreeVo = new OrderFreeVo();
        Date dateStart = DateUtil.parse2Date(startTime, "yyyy-MM-dd");
        Date dateEnd = DateUtil.parse2Date(endTime, "yyyy-MM-dd");
        //计算金额,每天房租*天数
        long[] distanceTime = DateUtil.getDistanceTime(dateEnd,dateStart);
        orderFreeVo.setPhonePrice(roomPojo.getMoney() * (distanceTime[0] == 0?1:distanceTime[0]));
        orderFreeVo.setTotalPrice(roomPojo.getMoney() * (distanceTime[0] == 0?1:distanceTime[0]));
        return CommonResult.SUCCESS(orderFreeVo);
    }

}
