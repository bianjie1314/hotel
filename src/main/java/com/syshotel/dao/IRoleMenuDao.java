package com.syshotel.dao;

import com.syshotel.pojo.RoleMenuPojo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IRoleMenuDao {

    /**
     * 添加信息
     * @param roleMenuPojo
     * @return
     */
    public int addBean(RoleMenuPojo roleMenuPojo);
    /**
     * 添加信息
     * @param roleMenuPojo
     * @return
     */
    public void insertBatch(List<RoleMenuPojo> roleMenuPojo);

    /**
     * 通过id删除
     * @param id
     */
    public void deleteById(int id);

    /**
     * 通过roleId删除
     * @param roleId
     */
    public void deleteByRoleId(int roleId);

    /**
     * 更新
     * @param roleMenuPojo
     */
    public void updateBean(RoleMenuPojo roleMenuPojo);

    /**
     * 查询满足条件的信息
     * @param paramMap
     * @return
     */
    public List<RoleMenuPojo> selectRoleMenuList(Map<String, Object> paramMap);

    /**
     * 查询满足条件的信息
     * @param roleId
     * @return
     */
    public List<Integer> selectMenuIdByRoleId(@Param("roleId") int roleId);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public RoleMenuPojo getById(int id);

    /**
     * 通过map条件删除数据
     * @param map
     */
    public void deleteByMap(HashMap map);
}
