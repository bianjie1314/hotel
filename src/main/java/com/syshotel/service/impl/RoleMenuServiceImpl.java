package com.syshotel.service.impl;

import com.syshotel.common.CommonResult;
import com.syshotel.common.MessageConstant;
import com.syshotel.dao.IMenuDao;
import com.syshotel.dao.IRoleMenuDao;
import com.syshotel.pojo.RoleMenuPojo;
import com.syshotel.pojo.vo.RoleMenuVo;
import com.syshotel.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleMenuServiceImpl implements IRoleMenuService {

    @Autowired
    IRoleMenuDao iRoleMenuDao;
    @Autowired
    IMenuDao iMenuDao;

    @Override
    public List<RoleMenuVo> getByRoleId(int roleId) {
        if (roleId <= 0){
            return null;
        }

        //获取已经分配了的权限列表
        List<Integer> menuIdsList = iRoleMenuDao.selectMenuIdByRoleId(roleId);
        //获取菜单列表
        List<RoleMenuVo> roleMenuVos = iMenuDao.selectNormalMenu();
        if (roleMenuVos != null && menuIdsList != null){
            for (RoleMenuVo roleMenuVo: roleMenuVos){
                if (menuIdsList.contains(roleMenuVo.getId())){
                    roleMenuVo.setCheck(true);
                }
            }
        }
        return roleMenuVos;
    }

    @Override
    public CommonResult updateAuthority(int roleId, String menuIds) {
        if (roleId <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }

        //删除之前的权限配置
        iRoleMenuDao.deleteByRoleId(roleId);
        //重新分配
        if (!StringUtils.isEmpty(menuIds)){
            String[] menuIdArr = menuIds.split(",");
            //批量插入
            List<RoleMenuPojo> roleMenuPojos = new ArrayList<>();
            for (String menuId:menuIdArr){
                RoleMenuPojo roleMenu = new RoleMenuPojo();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(Integer.parseInt(menuId));
                roleMenu.setCreateTime(new Date());
                roleMenuPojos.add(roleMenu);
            }
            iRoleMenuDao.insertBatch(roleMenuPojos);
        }
        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,null);
    }
}
