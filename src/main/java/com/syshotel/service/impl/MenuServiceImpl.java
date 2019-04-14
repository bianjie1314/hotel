package com.syshotel.service.impl;

import com.syshotel.common.*;
import com.syshotel.dao.IMenuDao;
import com.syshotel.dao.IRoleMenuDao;
import com.syshotel.pojo.MenuPojo;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class MenuServiceImpl implements IMenuService {


    @Autowired
    IMenuDao menuDao;
    @Autowired
    IRoleMenuDao roleMenuDao;

    @Override
    public CommonResult addBean(MenuPojo menuPojo) {
        if (menuPojo == null || StringUtils.isEmpty(menuPojo.getName())){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        int num = menuDao.countByName(menuPojo.getName());
        if (num > 0){
            return CommonResult.ERROR(MessageConstant.MENU_EXISTS);
        }
        menuPojo.setCreateTime(new Date());
         int result = menuDao.addBean(menuPojo);
        if (result <= 0){
            return CommonResult.ERROR(MessageConstant.ADD_FAILURE);
        }
        return CommonResult.SUCCESS(MessageConstant.ADD_SUCCESS,result);
    }

    @Override
    public CommonResult deleteById(int id) {
        if (id <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        menuDao.deleteById(id);
        //删除角色关联
        HashMap map = new HashMap();
        map.put("menuId",id);
        roleMenuDao.deleteByMap(map);

        //若是一级菜单,需要将其下子菜单关联取消
        map.clear();
        map.put("parentId",id);
        menuDao.deleteByMap(map);

        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    /**
     * 批量删除
     * @param idStr
     * @return
     */
    @Override
    public CommonResult deleteByChoiceId(String idStr) {
        if (StringUtils.isEmpty(idStr)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }

        String[] index = (idStr.substring(idStr.indexOf(',') + 1)).split(",");
        List<String> idList = Arrays.asList(index);
        HashMap map = new HashMap();
        map.put("ids",idList);
        menuDao.deleteByMap(map);

        //删除角色关联
        map.clear();
        map.put("menuIds",idList);
        roleMenuDao.deleteByMap(map);

        //若是一级菜单,需要将其下子菜单删除
        map.clear();
        map.put("parentIds",idList);
        menuDao.deleteByMap(map);
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    /**
     * 获取用户关联的权限菜单
     * @return
     */
    public CommonResult queryRoleMenu(UserPojo userPojo){
        if (userPojo == null || userPojo.getRoleId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        return CommonResult.SUCCESS(menuDao.queryMenuByRoleId(userPojo.getRoleId()));
    }


    /**
     * 查询菜单列表
     * */
    @Override
    public List<MenuPojo> selectMenuList(SearchVo searchVo, PageBean page) {
        Map<String,Object> paramMap = new HashMap<>();
        if (searchVo != null){
            if (!StringUtils.isEmpty(searchVo.getText())){
                paramMap.put("name",searchVo.getText());
            }
            if (!StringUtils.isEmpty(searchVo.getStartTime())){
                paramMap.put("startTime",searchVo.getStartTime());
            }
            if (!StringUtils.isEmpty(searchVo.getEndTime())){
                paramMap.put("endTime",searchVo.getEndTime());
            }
            if (!StringUtils.isEmpty(searchVo.getCategory())){
                paramMap.put("parent",searchVo.getCategory());
            }
            if (searchVo.getStatus() > 0){
                paramMap.put("status",searchVo.getStatus());
            }
        }
        if (page != null && page.getStart() >= 0){
            paramMap.put("start",page.getStart());
            paramMap.put("offset",page.getOffset());
        }
        return menuDao.queryByMap(paramMap);
    }

    /**
     * 更新菜单状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public CommonResult updateStatus(int id, int status) {
        if (id <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        MenuPojo menuPojo = new MenuPojo();
        //此处处理，反之前端不合法参数
        if (status == Constant.MENU_STATUS_NORMAL){
            menuPojo.setStatus(Constant.MENU_STATUS_NORMAL);
        }else if(status == Constant.MENU_STATUS_ENABEL){
            menuPojo.setStatus(Constant.MENU_STATUS_ENABEL);
        }
        menuPojo.setId(id);
        menuPojo.setUpdateTime(new Date());
        menuDao.updateBean(menuPojo);

        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,null);
    }

    @Override
    public MenuPojo getById(int id) {
        return menuDao.getById(id);
    }

    @Override
    public CommonResult updateBean(MenuPojo menuPojo) {
        if (menuPojo == null || menuPojo.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        menuPojo.setUpdateTime(new Date());
        menuDao.updateBean(menuPojo);
        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,null);
    }

}
