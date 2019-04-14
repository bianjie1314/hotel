package com.syshotel.common;

public interface Constant {


    //订单状态，1:未支付，2:待发货,3:待收获,4:已签收,5:已评价,6:取消订单,7:订单完成,8:退货申请，9:退货同意,10:退货拒绝,11:退货完成
    int ORDER_PAY_DOING = 1;
    int ORDER_DELIVERY = 2;
    int ORDER_GAINS = 3;
    int ORDER_PEN = 4;
    int ORDER_EVELATE = 5;
    int ORDER_CANCER = 6;
    int ORDER_FINISH = 7;
    int ORDER_RETURN_APPLAY = 8;
    int ORDER_RETURN_AGREE = 9;
    int ORDER_RETURN_REFUSE = 10;
    int ORDER_RETURN_FINISH = 11;


    int INFO_SHOW = 1; //显示信息获取的
    int INFO_CHANGE = 2;	//更改信息获取

    int USER_ADMIN = 1; //管理员
    int USER_SHOP = 2;	//商户
    int USER_COMMON = 3;	//普通用户


    int MENU_STATUS_NORMAL = 1;//菜单正常
    int MENU_STATUS_ENABEL = 2;//菜单禁用

    int SHOP_STATUS_NORMAL = 1;//店铺正常
    int SHOP_STATUS_ENABEL = 2;//店铺下架
}
