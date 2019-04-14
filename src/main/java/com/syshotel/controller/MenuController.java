package com.syshotel.controller;


import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.MenuPojo;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息接口
 */
@Controller
@RequestMapping(value="/menu")
public class MenuController {

    private static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IMenuService iMenuService;


    //菜单显示
    @RequestMapping(value="/menuLevelList",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult menuLevelList(HttpServletRequest request){
        logger.info("********** 进入 menuList 方法 ********** ");
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        return iMenuService.queryRoleMenu(userInfo);
    }



    //获取列表
    @RequestMapping(value="/getMenuList",method=RequestMethod.GET )
    // @ResponseBody
    public String getMenuList(SearchVo searchVo, PageBean page, Model model){
        logger.info("********** 进入 getMenuList 方法,searchVo={},page={}********** ",new Object[]{searchVo,page});
        model.addAttribute("menuList", iMenuService.selectMenuList(searchVo,page));
        model.addAttribute("searchVo", searchVo);
        return "admin/menu/menu-list";
    }

    //根据id获取
    @RequestMapping(value="/getMenuById/{menuId}",method=RequestMethod.GET )
    public String getMenuById(@PathVariable("menuId") int id,String view,Model model){
        logger.info("********** 进入 getMenuById 方法,id={}********** ",new Object[]{id});
        model.addAttribute("modifyMenuInfo",iMenuService.getById(id));
        return view;
    }

    //更新自己
    @RequestMapping(value="/update" )
    @ResponseBody
    public CommonResult updateMenu(@RequestBody MenuPojo menuPojo){
        logger.info("********** 进入 updateUser 方法,menuPojo={}********** ",new Object[]{menuPojo});
        return  iMenuService.updateBean(menuPojo);
    }

    //获取列表
    @RequestMapping(value="/getNeedMenuList",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult getParentMenuList(SearchVo searchVo, PageBean page){
        logger.info("********** 进入 getParentMenuList 方法,searchVo={},page={}********** ",new Object[]{searchVo,page});
        return CommonResult.SUCCESS(iMenuService.selectMenuList(searchVo, page));
    }


    //删除
    @RequestMapping(value="/delete/{menuId}",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult deleteMenu(@PathVariable("menuId")  int menuId){
        logger.info("********** 进入 deleteMenu 方法,menuId={}********** ",new Object[]{menuId});
        return iMenuService.deleteById(menuId);
    }


    //进行多选删除
    @RequestMapping(value = "/deleteMenuByChoice", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteMenuByChoice(String choiceId) {
        logger.info("************  进入  deleteMenuByChoice 方法,参数choiceId={} ************ ",new Object[]{ choiceId});
        return iMenuService.deleteByChoiceId(choiceId);
    }

    //进行更新状态
    @RequestMapping(value = "/updateStatus/{id}/{status}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable int id, @PathVariable int status) {
        logger.info("************  进入  updateStatus 方法,id={},status={} ************ ",new Object[]{id, status});
        return iMenuService.updateStatus(id,status);
    }


    //批量删除
    @RequestMapping(value="/add")
    @ResponseBody
    public CommonResult addMenu(@RequestBody  MenuPojo menuPojo){
        logger.info("********** 进入 addMenu 方法,menuPojo={}********** ",new Object[]{menuPojo});
        return iMenuService.addBean(menuPojo);
    }
}
