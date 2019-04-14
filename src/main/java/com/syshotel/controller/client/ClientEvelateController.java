package com.syshotel.controller.client;


import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.service.IEvelateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 评价接口
 */
@Controller
@RequestMapping(value="/client/evelate")
public class ClientEvelateController {

    private static Logger logger = LoggerFactory.getLogger(ClientEvelateController.class);

    @Autowired
    private IEvelateService iEvelateService;

    //获取手机对应的评论列表
    @RequestMapping(value="/getEvelatePage",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult getEvelatePage(SearchVo searchVo,PageBean pageBean, Model model){
        logger.info("********** 进入 client - getEvelatePage 方法,searchVo={},pageBean={} ********** ",new Object[]{searchVo,pageBean});
        CommonResult commonResult = iEvelateService.getEvelatePage(searchVo, pageBean);
        model.addAttribute("searchVo",searchVo);
        model.addAttribute("pageBean",pageBean);
        return commonResult;
    }


    //获取用户对应订单评论列表
    @RequestMapping(value="/getUserEvelatePage",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult getUserEvelatePage(SearchVo searchVo,PageBean pageBean, Model model){
        logger.info("********** 进入 client - getUserEvelatePage 方法,searchVo={},pageBean={} ********** ",new Object[]{searchVo,pageBean});
        CommonResult commonResult = iEvelateService.getEvelatePage(searchVo, pageBean);
        model.addAttribute("searchVo",searchVo);
        model.addAttribute("pageBean",pageBean);
        return commonResult;
    }
}
