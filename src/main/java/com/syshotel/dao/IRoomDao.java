package com.syshotel.dao;


import com.syshotel.pojo.RoomPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IRoomDao {

    /**
     * 添加信息
     * @param roomPojo
     * @return
     */
    public int addBean(RoomPojo roomPojo);

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
     * @param roomPojo
     */
    public void updateBean(RoomPojo roomPojo);

    /**
     * 查询满足条件的信息
     * @param paramMap
     * @return
     */
    public List<RoomPojo> selectRoomList(Map<String, Object> paramMap);


    /**
     * 统计满足条件的信息
     * @param paramMap
     * @return
     */
    public int countRoomList(Map<String, Object> paramMap);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public RoomPojo getById(int id);

}
