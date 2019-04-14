package com.syshotel.dao;

import com.syshotel.pojo.RolePojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IRoleDao {

    /**
     * 添加信息
     * @param rolePojo
     * @return
     */
    public int addBean(RolePojo rolePojo);

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
     * @param rolePojo
     */
    public void updateBean(RolePojo rolePojo);

    /**
     * 查询满足条件的信息
     * @param paramMap
     * @return
     */
    public List<RolePojo> selectRoleList(Map<String, Object> paramMap);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public RolePojo getById(int id);

    /**
     * 通过名称查询角色
     * @param name
     * @return
     */
    public int countByName(String name);
}
