package com.syshotel.service;

import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.MenuPojo;
import com.syshotel.pojo.UserPojo;

import java.util.List;

public interface IMenuService {


    /**
     * 添加
     * @param menuPojo
     * @return
     */
    public CommonResult addBean(MenuPojo menuPojo);


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
     * 获取用户关联的权限菜单
     * @return
     */
    public CommonResult queryRoleMenu(UserPojo userPojo);


    /**
     * 查询菜单列表
     * @param searchVo
     * @param page
     * @return
     */
    public List<MenuPojo> selectMenuList(SearchVo searchVo, PageBean page);

    /**
     * 更新菜单状态
     * @param id
     * @param status
     * @return
     */
    public CommonResult updateStatus(int id, int status);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    public MenuPojo getById(int id);

    /**
     * 更新
     * @param menuPojo
     * @return
     */
    public CommonResult updateBean(MenuPojo menuPojo);
}
