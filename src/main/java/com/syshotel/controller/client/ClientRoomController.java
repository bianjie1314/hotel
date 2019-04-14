package com.syshotel.controller.client;


import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.service.IRoomService;
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
 * 酒店房间接口
 */
@Controller
@RequestMapping(value="/client/room")
public class ClientRoomController {

    private static Logger logger = LoggerFactory.getLogger(ClientRoomController.class);

    @Autowired
    private IRoomService iRoomService;


    //最新的推荐列表
    @RequestMapping(value="/getRoomPage" )
   @ResponseBody
    public CommonResult getRoomPage(SearchVo searchVo,PageBean pageBean, Model model, HttpServletRequest request){
        logger.info("********** 进入 client - getRoomPage 方法,searchVo={},pageBean={} ********** ",new Object[]{searchVo,pageBean});
      //  UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        CommonResult commonResult = iRoomService.selectRoomPage(searchVo, pageBean,null);
        model.addAttribute("searchVo",searchVo);
        model.addAttribute("pageBean",pageBean);
        return commonResult;
    }

    //根据id获取
    @RequestMapping(value="/getRoomById/{roomId}",method=RequestMethod.GET )
    public String getPhoneById(@PathVariable("roomId") int id,String view,Model model){
        logger.info("********** 进入 getRoomById 方法,id={}********** ",new Object[]{id});
        model.addAttribute("roomInfo",iRoomService.getById(id));
        return view;
    }


}
