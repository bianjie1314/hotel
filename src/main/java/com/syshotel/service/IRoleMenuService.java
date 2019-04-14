package com.syshotel.service;

import com.syshotel.common.CommonResult;
import com.syshotel.pojo.vo.RoleMenuVo;

import java.util.List;


public interface IRoleMenuService {


    /**
     * 获取权限
     * @param roleId
     * @return
     */
    public List<RoleMenuVo> getByRoleId(int roleId);

    /**
     * 更新权限
     * @param roleId
     * @param menuIds
     * @return
     */
    public CommonResult updateAuthority(int roleId, String menuIds);
}
