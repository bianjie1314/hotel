package com.syshotel.dao;

import com.syshotel.common.CommonResult;
import com.syshotel.pojo.HotelPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IHotelDao {

    /**
     * 添加信息
     * @param hotelPojo
     * @return
     */
    public int addBean(HotelPojo hotelPojo);

    /**
     * 通过id删除
     * @param id
     */
    public void deleteById(int id);

    /**
     * 通过id集合遍历删除
     * @param ids
     */
    public void deleteByChoiceId(@Param("ids") List ids);

    /**
     * 更新
     * @param hotelPojo
     */
    public void updateBean(HotelPojo hotelPojo);

    /**
     * 查询满足条件的信息
     * @param paramMap
     * @return
     */
    public List<HotelPojo> selectHotelList(Map<String, Object> paramMap);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public HotelPojo getById(int id);


    /**
     * 获取酒店的编号和名称
     * @param userId
     * @return
     */
    public List<HotelPojo> getHotelInfo(@Param("userId") int userId);
}
