package com.syshotel.controller;


import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.EvelatePojo;
import com.syshotel.service.IEvelateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 评价接口
 */
@Controller
@RequestMapping(value="/evelate")
public class EvelateController {

    private static Logger logger = LoggerFactory.getLogger(EvelateController.class);

    @Autowired
    private IEvelateService iEvelateService;


    //获取列表
    @RequestMapping(value="/getEvelateList",method=RequestMethod.GET )
    // @ResponseBody
    public String getEvelateList(SearchVo searchVo, PageBean page, Model model){
        logger.info("********** 进入 getEvelateList 方法,searchVo={},page={}********** ",new Object[]{searchVo,page});
        model.addAttribute("evelateList", iEvelateService.selectEvelateList(searchVo,page));
        model.addAttribute("searchVo", searchVo);
        return "admin/evelate/evelate-list";
    }


    //删除
    @RequestMapping(value="/delete/{evelateId}",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult deleteEvelate(@PathVariable("evelateId") int evelateId){
        logger.info("********** 进入 deleteEvelate 方法,evelateId={}********** ",new Object[]{evelateId});
        return iEvelateService.deleteById(evelateId);
    }

    //进行多选删除
    @RequestMapping(value = "/deleteEvelateByChoice", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteEvelateByChoice(String choiceId) {
        logger.info("************  进入  deleteEvelateByChoice 方法,参数choiceId={} ************ ",new Object[]{ choiceId});
        return iEvelateService.deleteByChoiceId(choiceId);
    }

    //审核评论
    @RequestMapping(value = "/auditEvelate", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult auditEvelate(String choiceId,int status,HttpServletRequest request) {
        logger.info("************  进入  auditEvelate 方法,choiceId={},status={} ************ ",new Object[]{choiceId,status});
        return iEvelateService.auditEvelate(choiceId,status);
    }

    //更新
    @RequestMapping(value="/update",method=RequestMethod.PUT )
    @ResponseBody
    public CommonResult updateEvelate(EvelatePojo evelate){
        logger.info("********** 进入 updateEvelate 方法,evelate={}********** ",new Object[]{evelate});
        return iEvelateService.updateBean(evelate);
    }

}
