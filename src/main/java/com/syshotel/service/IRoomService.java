package com.syshotel.service;

import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.RoomPojo;
import com.syshotel.pojo.UserPojo;

import java.util.List;
import java.util.Map;

public interface IRoomService {

    /**
     * 添加信息
     * @param roomPojo
     * @param userPojo
     * @return
     */
    public CommonResult addBean(RoomPojo roomPojo, UserPojo userPojo);

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
     * @param roomPojo
     */
    public CommonResult updateBean(RoomPojo roomPojo);

    /**
     * 查询满足条件的信息
     * @param searchVo
     * @param pageBean
     * @param userInfo
     * @return
     */
    public List<RoomPojo> selectRoomList(SearchVo searchVo, PageBean pageBean, UserPojo userInfo);

    /**
     * 查询满足条件的信息
     * @param searchVo
     * @param pageBean
     * @return
     */
    public CommonResult selectRoomPage(SearchVo searchVo, PageBean pageBean, UserPojo userInfo);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public RoomPojo getById(int id);

    /**
     * 更新
     * @param map
     * @return
     */
    public CommonResult updateByMap(Map<String,Object> map);
}
