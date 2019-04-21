package com.syshotel.service.impl;

import com.syshotel.common.*;
import com.syshotel.dao.IHotelDao;
import com.syshotel.dao.IPictureDao;
import com.syshotel.pojo.HotelPojo;
import com.syshotel.pojo.PicturePojo;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class HoteServiceImpl implements IHotelService {

    @Autowired
    IHotelDao hotelDao;
    @Autowired
    IPictureDao iPictureDao;

    @Override
    public CommonResult addBean(HotelPojo hotelPojo,UserPojo userInfo) {
        if (userInfo == null || userInfo.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        hotelPojo.setUserId(userInfo.getId());
        hotelPojo.setCreateTime(new Date());
        int result = hotelDao.addBean(hotelPojo);
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
        hotelDao.deleteById(id);
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }


    @Override
    public CommonResult deleteByChoiceId(String idStr) {
        if (StringUtils.isEmpty(idStr)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        String[] index = (idStr.substring(idStr.indexOf(',') + 1)).split(",");
        hotelDao.deleteByChoiceId( Arrays.asList(index));
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    @Override
    public CommonResult updateBean(HotelPojo hotelPojo) {
        if (hotelPojo == null || hotelPojo.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        hotelPojo.setUpdateTime(new Date());
        hotelDao.updateBean(hotelPojo);
        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,null);
    }

    @Override
    public HotelPojo getById(int id) {
        HotelPojo hotelPojo = hotelDao.getById(id);
        if (hotelPojo != null){
            if (!StringUtils.isEmpty(hotelPojo.getPictureIds())){
                List<PicturePojo> byIds = iPictureDao.getByIds(Arrays.asList(hotelPojo.getPictureIds().split(",")));
                hotelPojo.setPictures(byIds);
            }
        }
        return hotelPojo;
    }

    @Override
    public CommonResult getHotelName(UserPojo userInfo) {
        if (userInfo == null || userInfo.getRoleId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        int userId = 0;
        if (userInfo.getRoleId() != Constant.USER_ADMIN){//非管理员
            userId = userInfo.getId();
        }
        return CommonResult.SUCCESS(hotelDao.getHotelInfo(userId));
    }


    @Override
    public CommonResult selectHotelList(SearchVo searchVo, PageBean page,UserPojo userInfo) {
        Map<String,Object> paramMap = new HashMap<>();
        if (userInfo != null && userInfo.getRoleId() != Constant.USER_ADMIN){//非管理员
            paramMap.put("userId",userInfo.getId());
        }
        if (searchVo != null){
            if (!StringUtils.isEmpty(searchVo.getText())){
                paramMap.put("name",searchVo.getText());
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
        return CommonResult.SUCCESS(hotelDao.selectHotelList(paramMap));
    }


    /**
     * 更新菜单状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public CommonResult updateStatus(int id, int status) {
        if (id <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        HotelPojo hotelPojo = new HotelPojo();
        //此处处理，反之前端不合法参数
        if (status == Constant.SHOP_STATUS_NORMAL){
            hotelPojo.setStatus(Constant.SHOP_STATUS_NORMAL);
        }else if(status == Constant.SHOP_STATUS_ENABEL){
            hotelPojo.setStatus(Constant.SHOP_STATUS_ENABEL);
        }
        hotelPojo.setId(id);
        hotelPojo.setUpdateTime(new Date());
        hotelDao.updateBean(hotelPojo);

        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,null);
    }

}
