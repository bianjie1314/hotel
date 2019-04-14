package com.syshotel.controller.common;

import com.syshotel.common.CommonResult;
import com.syshotel.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ChangePwdController {
	
	private static Logger logger = LoggerFactory.getLogger(ChangePwdController.class);

	@Autowired
	private IUserService iUserService;

	//修改密码
	@RequestMapping(value="/changePwd",method=RequestMethod.GET )
	@ResponseBody
	public CommonResult changePwd(int id, String oldPassword,String newPassword, HttpServletRequest request){
		logger.info("********** 进入 changePwd 方法,id={},oldPassword={},newPassword={}********** ",new Object[]{id,oldPassword,newPassword});
		return iUserService.changePwd(id,oldPassword,newPassword);
	}

	//忘记密码
	@RequestMapping(value="/forgetPwd",method=RequestMethod.GET )
	@ResponseBody
	public CommonResult forgetPwd(String username, String mobile,String newpassword, HttpServletRequest request){
		logger.info("********** 进入 forgetPwd 方法,username={},mobile={},newpassword={}********** ",new Object[]{username,mobile,newpassword});

		return iUserService.forgetPwd(username,mobile,newpassword);
	}
}
