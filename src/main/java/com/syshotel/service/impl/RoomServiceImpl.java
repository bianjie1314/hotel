package com.syshotel.service.impl;

import com.syshotel.common.*;
import com.syshotel.dao.IPictureDao;
import com.syshotel.dao.IRoomDao;
import com.syshotel.dao.IHotelDao;
import com.syshotel.pojo.PicturePojo;
import com.syshotel.pojo.RoomPojo;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class RoomServiceImpl implements IRoomService {

    @Autowired
    IRoomDao iRoomDao;
    @Autowired
    IHotelDao iHotelDao;
    @Autowired
    IPictureDao iPictureDao;

    @Override
    public CommonResult addBean(RoomPojo roomPojo, UserPojo userPojo) {
        if (roomPojo == null || userPojo == null || userPojo.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        //获取酒店编号
        if (roomPojo.getHotelId() <= 0){
            return CommonResult.ERROR(MessageConstant.HOTEL_ERROR);
        }
        roomPojo.setStatus(Constant.ROOM_STATUS_NORMAL);
        roomPojo.setCreateTime(new Date());
        int result = iRoomDao.addBean(roomPojo);
        if (result <= 0){
            return CommonResult.ERROR(MessageConstant.RUN_ERROR);
        }
        return CommonResult.SUCCESS(MessageConstant.ADD_SUCCESS,result);
    }

    @Override
    public CommonResult deleteById(int id) {
        if (id <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        iRoomDao.deleteById(id);
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    @Override
    public CommonResult deleteByChoiceId(String idStr) {
        if (StringUtils.isEmpty(idStr)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        String[] index = (idStr.substring(idStr.indexOf(',') + 1)).split(",");
        iRoomDao.deleteByChoiceId( Arrays.asList(index));
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    @Override
    public CommonResult updateBean(RoomPojo roomPojo) {
        if (roomPojo == null || roomPojo.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        //获取酒店编号
        if (roomPojo.getHotelId() <= 0){
            return CommonResult.ERROR(MessageConstant.HOTEL_ERROR);
        }
        roomPojo.setUpdateTime(new Date());
        iRoomDao.updateBean(roomPojo);
        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,null);
    }


    @Override
    public List<RoomPojo> selectRoomList(SearchVo searchVo, PageBean page,UserPojo userInfo) {
        return iRoomDao.selectRoomList(getQueryMap(searchVo,page,userInfo));
    }

    //查询条件
    private Map<String,Object> getQueryMap(SearchVo searchVo, PageBean page,UserPojo userInfo){
        Map<String,Object> paramMap = new HashMap<>();
        if (userInfo != null && userInfo.getRoleId() != Constant.USER_ADMIN){//非管理员
            paramMap.put("userId",userInfo.getId());
        }
        if (searchVo != null){
            if (!StringUtils.isEmpty(searchVo.getText())){
                paramMap.put("number",searchVo.getText());
            }
            if (searchVo.getIndex() >0 ){
                paramMap.put("hotelId",searchVo.getIndex());
            }
            if (!StringUtils.isEmpty(searchVo.getCategory())){
                paramMap.put("hotelInfo",searchVo.getCategory());
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
    public CommonResult selectRoomPage(SearchVo searchVo, PageBean page,UserPojo userInfo) {
        Map<String, Object> queryMap = getQueryMap(searchVo, page,userInfo);
        //数量为0
        int count = iRoomDao.countPage(queryMap);
        if (count == 0){
            return CommonResult.SUCCESS(0,null);
        }
        page.setTotal(count);
        List<RoomPojo> roomPojos = iRoomDao.selectPage(queryMap);
        if (roomPojos != null && roomPojos.size() > 0){
            for (RoomPojo p: roomPojos ) {
                if (!StringUtils.isEmpty(p.getPictureIds())){
                    List<PicturePojo> byIds = iPictureDao.getByIds(Arrays.asList(p.getPictureIds().split(",")));
                    p.setPictures(byIds);
                }
            }
        }
        return CommonResult.SUCCESS(count,roomPojos);
    }

    @Override
    public RoomPojo getById(int id) {
        RoomPojo roomPojo = iRoomDao.getById(id);
        if (roomPojo != null) {
            if (!StringUtils.isEmpty(roomPojo.getPictureIds())) {
                List<PicturePojo> byIds = iPictureDao.getByIds(Arrays.asList(roomPojo.getPictureIds().split(",")));
                roomPojo.setPictures(byIds);
            }
        }
        return roomPojo;
    }

    @Override
    public CommonResult updateByMap(Map<String, Object> map) {
        iRoomDao.updateByMap(map);
        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,null);
    }

}
