package com.syshotel.controller;


import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.RoomPojo;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 酒店房间接口
 */
@Controller
@RequestMapping(value="/room")
public class RoomController {

    private static Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private IRoomService iRoomService;


    //获取列表
    @RequestMapping(value="/getRoomList",method=RequestMethod.GET )
   // @ResponseBody
    public String getRoomList(SearchVo searchVo, PageBean page, Model model, HttpServletRequest request){
        logger.info("********** 进入 getRoomList 方法,searchVo={},page={}********** ",new Object[]{searchVo,page});
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        model.addAttribute("roomList", iRoomService.selectRoomList(searchVo,page,userInfo));
        model.addAttribute("searchVo", searchVo);
        return "admin/room/room-list";
    }

    //根据id获取
    @RequestMapping(value="/getRoomById/{roomId}",method=RequestMethod.GET )
    public String getRoomById(@PathVariable("roomId") int id,String view,Model model){
        logger.info("********** 进入 getRoomById 方法,id={}********** ",new Object[]{id});
        model.addAttribute("roomInfo",iRoomService.getById(id));
        return view;
    }

    //删除
    @RequestMapping(value="/delete/{roomId}",method=RequestMethod.GET )
    @ResponseBody
    public CommonResult deleteRoom(@PathVariable("roomId")  int roomId){
        logger.info("********** 进入 deleteRoom 方法,roomId={}********** ",new Object[]{roomId});
        return iRoomService.deleteById(roomId);
    }


    //进行多选删除
    @RequestMapping(value = "/deleteRoomByChoice", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteRoomByChoice(String choiceId) {
        logger.info("************  进入  deleteRoomByChoice 方法,参数choiceId={} ************ ",new Object[]{ choiceId});
        return iRoomService.deleteByChoiceId(choiceId);
    }

    //更新
    @RequestMapping(value="/update")
    @ResponseBody
    public CommonResult updateRoom(@RequestBody RoomPojo roomPojo, HttpServletRequest request){
        logger.info("********** 进入 updateRoom 方法,roomPojo={}********** ",new Object[]{roomPojo});
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        Object attribute = request.getSession().getAttribute("pic" + userInfo.getId());
        if (attribute != null){
            roomPojo.setPictureIds((String)attribute);
        }
        request.getSession().removeAttribute("pic" + userInfo.getId());
        return  iRoomService.updateBean(roomPojo);
    }

    //新增
    @RequestMapping(value="/add")
    @ResponseBody
    public CommonResult addRoom(@RequestBody RoomPojo roomPojo, HttpServletRequest request){
        logger.info("********** 进入 addRoom 方法,roomPojo={}********** ",new Object[]{roomPojo});
        UserPojo userInfo = (UserPojo)request.getSession().getAttribute("userInfo");
        Object attribute = request.getSession().getAttribute("pic" + userInfo.getId());
        if (attribute != null){
           roomPojo.setPictureIds((String)attribute);
        }
        request.getSession().removeAttribute("pic" + userInfo.getId());
        return iRoomService.addBean(roomPojo,userInfo);
    }

}
