package com.syshotel.controller.client;


import com.syshotel.common.CommonResult;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息接口
 */
@Controller
@RequestMapping(value="/client/user")
public class ClientUserController {

    private static Logger logger = LoggerFactory.getLogger(ClientUserController.class);

    @Autowired
    private IUserService iUserService;

    //更新
    @RequestMapping(value="/update")
    @ResponseBody
    public CommonResult updateUser(@RequestBody UserPojo user, HttpServletRequest request){
        logger.info("********** 进入 updateUser 方法,user={}********** ",new Object[]{user});
        // 更新缓存
        CommonResult commonResult = iUserService.updateBean(user);
        if (commonResult.isResult()){
            request.getSession().setAttribute("clientUserInfo",commonResult.getData());
        }
        return commonResult;
    }


}
