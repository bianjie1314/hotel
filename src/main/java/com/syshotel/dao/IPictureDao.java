package com.syshotel.dao;

import com.syshotel.pojo.PicturePojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IPictureDao {

    /**
     * 添加信息
     * @param picturePojo
     * @return
     */
    public int addBean(PicturePojo picturePojo);

    /**
     * 通过id删除
     * @param id
     */
    public void deleteById(int id);

    /**
     * 更新
     * @param picturePojo
     */
    public void updateBean(PicturePojo picturePojo);

    /**
     * 查询满足条件的信息
     * @param paramMap
     * @return
     */
    public List<PicturePojo> selectPictureList(Map<String, Object> paramMap);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public PicturePojo getById(int id);

    /**
     * 通过编号获取
     * @param ids
     * @return
     */
    public List<PicturePojo> getByIds(@Param("ids") List<String> ids);
}
