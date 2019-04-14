package com.syshotel.common;

public interface MessageConstant {

    String LOGIN_FAILURE = "用户名或密码错误";
    String REGECT_LOGIN = "该账号不支持登陆";
    String MOBILE_HAS_REG = "该手机号已经注册过";
    String LOGIN_SUCCESS = "登陆成功";
    String NEED_LOGIN = "请先登陆";

    String PARAM_ERROR = "参数错误";
    String HOTEL_ERROR = "酒店信息获取失败";
    String RUN_ERROR = "运行错误";
    String VCODE_ERROR = "验证码错误";
    String OLD_PWD_ERROR = "原密码错误";
    String USER_NO_EXISTS = "用户信息不存在";
    String USER_CHECK_ERROR = "用户信息验证错误";

    String PARAM＿CODE = "1001";

    String LOGIN_EXIT = "退出登陆成功";

    String UPDATE_SUCCESS = "更新成功";
    String UPDATE_FAILURE = "更新失败";

    String MODIFY_SUCCESS = "修改成功";
    String MODIFY_FAILURE = "修改失败";


    String ADD_SUCCESS = "添加成功";
    String ADD_FAILURE = "添加失败";

    String DELETE_SUCCESS = "删除成功";
    String DELETE_FAILURE = "删除失败";



    String ORDER_NO_EXISTS = "订单不存在";
    String ORDER_NO_PAY = "订单未支付";
    String ORDER_HAVE_DELIVERY = "订单已发货";
    String ORDER_HAVE_CANCER = "订单已取消";
    String ORDER_DELIVERY_SUCCESS = "发货成功";
    String ORDER_NO_RETURN_APPLAY= "订单不是退货申请状态";
    String ORDER_RETURN_APPLAY_SOLVE_SUCCESS = "退货申请处理成功";

    String ROLE_EXISTS = "角色已经存在";
    String MENU_EXISTS = "菜单已经存在";
    String MOBILE_EXISTS = "该手机号已经存在";


    String FILE_UPLOAD_SUCCESS = "文件上传成功";
    String FILE_UPLOAD_FAILURE = "文件上传失败";

    String PHONE_NO_EXISTS = "商品不存在";

    String PAY_SUCCESS = "支付成功";
}
