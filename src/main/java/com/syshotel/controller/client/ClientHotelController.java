package com.syshotel.controller.client;


import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.HotelPojo;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IHotelService;
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
 * 酒店接口
 */
@Controller
@RequestMapping(value="/client/hotel")
public class ClientHotelController {

    private static Logger logger = LoggerFactory.getLogger(ClientHotelController.class);

    @Autowired
    private IHotelService iHotelService;


    //获取列表
    @RequestMapping(value="/getHotelPage" )
    @ResponseBody
    public CommonResult getHotelPage(SearchVo searchVo,PageBean pageBean, Model model, HttpServletRequest request){
        logger.info("********** 进入 client - getHotelPage 方法,searchVo={},pageBean={} ********** ",new Object[]{searchVo,pageBean});
        CommonResult commonResult = iHotelService.selectHotelList(searchVo, pageBean,null);
        model.addAttribute("searchVo",searchVo);
        model.addAttribute("pageBean",pageBean);
        return commonResult;
    }

}
