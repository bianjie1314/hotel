package com.syshotel.common;

public interface Constant {


    //订单状态，1待支付,2:预订中,3正常入住,4取消订单,5延长入住时间，6已退房，7已评价
    int ORDER_PAY_DOING = 1;
    int ORDER_IN_PRE = 2;
    int ORDER_IN_USE = 3;
    int ORDER_CANCER = 4;
    int ORDER_CONTINUE_USE = 5;
    int ORDER_FINISH = 6;
    int ORDER_EVELATE = 7;


    int INFO_SHOW = 1; //显示信息获取的
    int INFO_CHANGE = 2;	//更改信息获取

    int USER_ADMIN = 1; //管理员
    int USER_SHOP = 2;	//商户
    int USER_COMMON = 3;	//普通用户


    int MENU_STATUS_NORMAL = 1;//菜单正常
    int MENU_STATUS_ENABEL = 2;//菜单禁用

    int SHOP_STATUS_NORMAL = 1;//店铺正常
    int SHOP_STATUS_ENABEL = 2;//店铺下架


    int ROOM_STATUS_NORMAL = 1;//正常
    int ROOM_STATUS_USE = 2;//使用中
    int ROOM_STATUS_ENABEL = 3;//下线


    int WALLET_ADD_1 = 1;//充值
    int WALLET_PAY_2 = 2;//付款
    int WALLET_CANCER_3 = 3;//退款
}
