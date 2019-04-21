package com.syshotel.service.impl;

import com.syshotel.common.*;
import com.syshotel.dao.IEvelateDao;
import com.syshotel.dao.IOrderProcessLogDao;
import com.syshotel.dao.IOrdersDao;
import com.syshotel.pojo.EvelatePojo;
import com.syshotel.pojo.OrderProcessLogPojo;
import com.syshotel.pojo.OrdersPojo;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IEvelateService;
import com.syshotel.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class EvelateServiceImpl implements IEvelateService {

    @Autowired
    IEvelateDao iEvelateDao;
    @Autowired
    IOrdersDao iOrdersDao;
    @Autowired
    IOrderProcessLogDao orderProcessLogDao;

    @Transactional
    @Override
    public CommonResult addBean(EvelatePojo evelatePojo,UserPojo user) {
        if (evelatePojo.getOrderId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        evelatePojo.setCreateTime(new Date());
        evelatePojo.setStatus(1);
        evelatePojo.setUserId(user.getId());
        int result =  iEvelateDao.addBean(evelatePojo);

        //更新订单状态
        OrdersPojo ordersPojo = new OrdersPojo();
        ordersPojo.setUpdateTime(new Date());
        ordersPojo.setStatus(Constant.ORDER_EVELATE);
        ordersPojo.setId(evelatePojo.getOrderId());
        iOrdersDao.updateBean(ordersPojo);
        //添加记录
        OrderProcessLogPojo orderProcessLogPojo = new OrderProcessLogPojo();
        orderProcessLogPojo.setCreateTime(new Date());
        orderProcessLogPojo.setOrderId(evelatePojo.getOrderId());
        orderProcessLogPojo.setStatus(Constant.ORDER_EVELATE);
        orderProcessLogPojo.setDoUserId(user.getId());
        orderProcessLogDao.addBean(orderProcessLogPojo);

        return CommonResult.SUCCESS(MessageConstant.ADD_SUCCESS,result);
    }

    @Override
    public CommonResult deleteById(int id) {
        if (id <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        iEvelateDao.deleteById(id);
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    @Override
    public CommonResult deleteByChoiceId(String idStr) {
        if (StringUtils.isEmpty(idStr)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        String[] index = (idStr.substring(idStr.indexOf(',') + 1)).split(",");
        iEvelateDao.deleteByChoiceId( Arrays.asList(index));
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }


    @Override
    public CommonResult updateBean(EvelatePojo evelatePojo) {
        if (evelatePojo == null || evelatePojo.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        iEvelateDao.updateBean(evelatePojo);
        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,null);
    }

    @Override
    public List<EvelatePojo> selectEvelateList(SearchVo searchVo,  PageBean page) {
        return iEvelateDao.selectEvelateList(getQueryMap(searchVo,page));
    }

    private Map<String,Object> getQueryMap(SearchVo searchVo,  PageBean page){
        Map<String,Object> paramMap = new HashMap<>();
        if (searchVo != null){
            if (!StringUtils.isEmpty(searchVo.getText())){
                paramMap.put("inputCheck","%"+searchVo.getText()+"%");
            }
            if (!StringUtils.isEmpty(searchVo.getCategory())){
                paramMap.put("status",searchVo.getCategory());
            }
            if (!StringUtils.isEmpty(searchVo.getStartTime())){
                paramMap.put("startTime",searchVo.getStartTime());
            }
            if (!StringUtils.isEmpty(searchVo.getEndTime())){
                paramMap.put("endTime",searchVo.getEndTime());
            }
            if (searchVo.getIndex() > 0){
                paramMap.put("roomId",searchVo.getIndex());
            }
            if (searchVo.getStatus() > 0){
                paramMap.put("status",searchVo.getStatus());
            }
            if (searchVo.getUserFlag() > 0){
                paramMap.put("userId",searchVo.getUserFlag());
            }
        }
        if (page != null && page.getStart() >= 0){
            paramMap.put("start",page.getStart());
            paramMap.put("offset",page.getOffset());
        }
        return paramMap;
    }

    @Transactional
    @Override
    public CommonResult auditEvelate(String ids, int status) {
        if (StringUtils.isEmpty(ids) || status <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        String[] index = (ids.substring(ids.indexOf(',') + 1)).split(",");
        iEvelateDao.updateStatus(Arrays.asList(index),status);
        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,null);
    }

    @Override
    public CommonResult getEvelatePage(SearchVo searchVo, PageBean pageBean) {
        Map<String, Object> queryMap = getQueryMap(searchVo, pageBean);
        //数量为0
        int count = iEvelateDao.countEvelateList(queryMap);
        if (count == 0){
            return CommonResult.SUCCESS(0,null);
        }
        pageBean.setTotal(count);
        return CommonResult.SUCCESS(count,iEvelateDao.selectEvelateList(queryMap));
    }

    @Override
    public EvelatePojo getById(int id) {
        return iEvelateDao.getById(id);
    }
}
