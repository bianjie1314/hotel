package com.syshotel.controller.common;

import com.syshotel.common.CommonResult;
import com.syshotel.common.Constant;
import com.syshotel.common.MessageConstant;
import com.syshotel.pojo.UserPojo;
import com.syshotel.service.IUserService;
import com.syshotel.utils.VerifyCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IUserService iUserService;

	//登陆
	@RequestMapping(value="/login",method=RequestMethod.GET )
	@ResponseBody
	public CommonResult login(String mobile, String password,String vcode,String type, HttpServletRequest request){
		logger.info("********** 进入 login 方法,mobile={},password={},vcode={},type={} ********** ",new Object[]{mobile,password,vcode,type});

		//判断输入的参数是否存在空值
		if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(vcode)){
			logger.info("************ 完成 login 方法,结果-参数异常************ ");
			return CommonResult.ERROR(MessageConstant.PARAM＿CODE,MessageConstant.PARAM_ERROR);
		}
		if(!vcode.toLowerCase().equals(VerifyCodeUtils.VCODE.toLowerCase())){	//验证码
			return CommonResult.ERROR(MessageConstant.PARAM＿CODE,MessageConstant.VCODE_ERROR);
		}
		UserPojo userPojos = iUserService.getByMobileAndPwd(mobile,password);
		if (userPojos != null){ //登陆成功
			if (StringUtils.isEmpty(type)){//商户与管理员
				//缓存用户信息到session中
				request.getSession().setAttribute("userInfo", userPojos);
			}else {//普通用户
				if (userPojos.getRoleId() != Constant.USER_COMMON){	//商户与管理员使用客户端登陆的情况
					return CommonResult.ERROR(MessageConstant.REGECT_LOGIN);
				}
				request.getSession().setAttribute("clientUserInfo", userPojos);
			}
			return CommonResult.SUCCESS(MessageConstant.LOGIN_SUCCESS,userPojos);
		}else { //登陆失败
			return CommonResult.ERROR(MessageConstant.LOGIN_FAILURE);
		}
	}


	//退出登陆
	@RequestMapping(value="/exit",method=RequestMethod.GET)
	@ResponseBody
	public CommonResult exit(HttpServletRequest request){
		logger.info("********** 进入 exit 方法********** ");
		//清空缓存
		request.getSession().invalidate();
		return CommonResult.SUCCESS(MessageConstant.LOGIN_EXIT);
	}


	//获取验证码
	@RequestMapping(value = "/verify-code", method = RequestMethod.GET)
	@ResponseBody
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws  IOException {
		logger.info("************ 进入login-getVerifyCode方法************ ");

		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 生成随机字串
		VerifyCodeUtils.VCODE = VerifyCodeUtils.generateVerifyCode(4);
		/*String sessionId = request.getSession().getId();*/
		logger.info("session_id:" + request.getSession().getId());
		// 生成图片
		int w = 100, h = 30;
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), VerifyCodeUtils.VCODE);
		logger.info("************ 完成login-getVerifyCode方法,verifyCode={}************ ",new Object[]{VerifyCodeUtils.VCODE});
	}
}
