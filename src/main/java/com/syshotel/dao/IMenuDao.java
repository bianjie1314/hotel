package com.syshotel.dao;

import com.syshotel.pojo.MenuPojo;
import com.syshotel.pojo.vo.MenuViewVo;
import com.syshotel.pojo.vo.RoleMenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IMenuDao {

    /**
     * 添加信息
     * @param menuPojo
     * @return
     */
    public int addBean(MenuPojo menuPojo);

    /**
     * 通过id删除
     * @param id
     */
    public void deleteById(int id);


    /**
     * 更新
     * @param menuPojo
     */
    public void updateBean(MenuPojo menuPojo);

    /**
     * 查询满足条件的信息
     * @param paramMap
     * @return
     */
    public List<MenuPojo> queryByMap(Map<String, Object> paramMap);

    /**
     * 查询正常的menu
     * @return
     */
    public List<RoleMenuVo> selectNormalMenu();

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public MenuPojo getById(int id);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public MenuPojo findMenuItemByParentId(int id);


    /**
     * 查询角色对应的权限菜单列表（两级）
     * @param roleId
     * @return
     */
    public List<MenuViewVo> queryMenuByRoleId(@Param("roleId") int roleId);

    /**
     * 通过map传入条件删除
     * @param map
     */
    public void deleteByMap(HashMap map);



    /**
     * 通过名称查询菜单
     * @param name
     * @return
     */
    public int countByName(String name);
}
