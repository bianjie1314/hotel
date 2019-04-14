package com.syshotel.controller;


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
@RequestMapping(value="/hotel")
public class HotelController {

    private static Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private IHotelService iHotelService;


    //获取列表
    @RequestMapping(value="/getHotelList",method=RequestMethod.GET )
    // @ResponseBody
    public String getHotelList(SearchVo searchVo, PageBean page, Model model, HttpServletRequest request){
        logger.info("********** 进入 getHotelList 方法,searchVo={},page={}********** ",new Object[]{searchVo,page});
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        model.addAttribute("hotelList", iHotelService.selectHotelList(searchVo,page,userInfo).getData());
        model.addAttribute("searchVo", searchVo);
        return "admin/hotel/hotel-list";
    }

    //最新的推荐列表
    @RequestMapping(value="/getHotelName" )
    @ResponseBody
    public CommonResult getHotelName( HttpServletRequest request){
        logger.info("********** 进入 getHotelPage 方法 ********** ",new Object[]{});
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        return iHotelService.getHotelName(userInfo);
    }

    //进行更新状态
    @RequestMapping(value = "/updateStatus/{id}/{status}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable int id, @PathVariable int status) {
        logger.info("************  进入  updateStatus 方法,id={},status={} ************ ",new Object[]{id, status});
        return iHotelService.updateStatus(id,status);
    }

    //删除
    @RequestMapping(value="/delete/{hotelId}",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult deleteHotel(@PathVariable("hotelId") int hotelId){
        logger.info("********** 进入 deleteHotel 方法,hotelId={}********** ",new Object[]{hotelId});
        return iHotelService.deleteById(hotelId);
    }

    //进行多选删除
    @RequestMapping(value = "/deleteHotelByChoice", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteHotelByChoice(String choiceId) {
        logger.info("************  进入  deleteHotelByChoice 方法,参数choiceId={} ************ ",new Object[]{ choiceId});
        return iHotelService.deleteByChoiceId(choiceId);
    }


    //更新
    @RequestMapping(value="/update")
    @ResponseBody
    public CommonResult updateHotel(HotelPojo hotelPojo){
        logger.info("********** 进入 updateHotel 方法,hotelPojo={}********** ",new Object[]{hotelPojo});
        return iHotelService.updateBean(hotelPojo);
    }

    //新增
    @RequestMapping(value="/add",method=RequestMethod.POST )
    @ResponseBody
    public CommonResult addHotel(HotelPojo hotelPojo){
        logger.info("********** 进入 addHotel 方法,hotelPojo={}********** ",new Object[]{hotelPojo});
        return iHotelService.addBean(hotelPojo);
    }

}
