package com.syshotel.controller;


import com.syshotel.common.CommonResult;
import com.syshotel.service.IRoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户信息接口
 */
@Controller
@RequestMapping(value="/roleMenu")
public class RoleMenuController {

    private static Logger logger = LoggerFactory.getLogger(RoleMenuController.class);

    @Autowired
    private IRoleMenuService iRoleMenuService;



    //根据id获取
    @RequestMapping(value="/getAuthority/{roleId}",method=RequestMethod.GET )
    public String getAuthority(@PathVariable("roleId") int roleId,String view,Model model){
        logger.info("********** 进入 roleMenu-getAuthority 方法,roleId={}********** ",new Object[]{roleId});
        model.addAttribute("authorityInfo",iRoleMenuService.getByRoleId(roleId));
        model.addAttribute("roleId",roleId);
        return view;
    }

    //根据id获取
    @RequestMapping(value="/update/{roleId}/{menuId}" )
    @ResponseBody
    public CommonResult update(@PathVariable("roleId") int roleId,@PathVariable("menuId")String menuIds){
        logger.info("********** 进入 roleMenu-update 方法,roleId={},menuIds={} ********** ",new Object[]{roleId,menuIds});
        return iRoleMenuService.updateAuthority(roleId,menuIds);
    }

}
