package com.syshotel.service;

import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.HotelPojo;
import com.syshotel.pojo.UserPojo;

import java.util.List;

public interface IHotelService {

    /**
     * 添加信息
     * @param hotelPojo
     * @return
     */
    public CommonResult addBean(HotelPojo hotelPojo);

    /**
     * 通过id删除
     * @param id
     */
    public CommonResult deleteById(int id);

    /**
     * 通过id遍历删除
     * @param idStr
     */
    public CommonResult deleteByChoiceId(String idStr);

    /**
     * 更新
     * @param hotelPojo
     */
    public CommonResult updateBean(HotelPojo hotelPojo);

    /**
     * 查询满足条件的信息
     * @param searchVo
     * @param page
     * @param userInfo
     * @return
     */
    public CommonResult selectHotelList(SearchVo searchVo, PageBean page, UserPojo userInfo);

    /**
     * 更新状态
     * @param id
     * @param status
     * @return
     */
    public CommonResult updateStatus(int id, int status) ;

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public HotelPojo getById(int id);

    /**
     * 获取酒店的名称
     * @param userInfo
     * @return
     */
    public CommonResult getHotelName(UserPojo userInfo);
}
