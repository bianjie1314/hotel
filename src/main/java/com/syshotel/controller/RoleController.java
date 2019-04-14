package com.syshotel.controller;


import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.RolePojo;
import com.syshotel.service.IRoleService;
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
 * 系统角色信息接口
 */
@Controller
@RequestMapping(value="/role")
public class RoleController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService iRoleService;



    //获取列表
    @RequestMapping(value="/getRoleList",method=RequestMethod.GET )
    public String getRoleList(SearchVo searchVo, PageBean page, Model model){
        logger.info("********** 进入 getRoleList 方法,searchVo={},page={}********** ",new Object[]{searchVo,page});
        model.addAttribute("roleList", iRoleService.selectRoleList(searchVo,page));
        model.addAttribute("searchVo", searchVo);
        return "admin/role/role-list";
    }

    //获取列表,返回json
    @RequestMapping(value="/getRoleListJson",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult getRoleListJson(SearchVo searchVo, PageBean page){
        logger.info("********** 进入 getRoleListJson 方法,searchVo={},page={}********** ",new Object[]{searchVo,page});
        return CommonResult.SUCCESS(iRoleService.selectRoleList(searchVo,page));
    }

    //删除
    @RequestMapping(value="/delete/{id}",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult deleteRole(@PathVariable("id")  int id){
        logger.info("********** 进入 deleteOrders 方法,id={}********** ",new Object[]{id});
        return iRoleService.deleteById(id);
    }

    //批量删除
    @RequestMapping(value="/deleteRoleByChoice",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult deleteRoleByChoice(String choiceId){
        logger.info("********** 进入 deleteRoleByChoice 方法,choiceId={}********** ",new Object[]{choiceId});
        return iRoleService.deleteByChoiceId(choiceId);
    }

    //添加
    @RequestMapping(value="/add")
    @ResponseBody
    public CommonResult addRole(RolePojo role){
        logger.info("********** 进入 addRole 方法,role={}********** ",new Object[]{role});
        return iRoleService.addBean(role);
    }

}
