package com.syshotel.service;

import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.UserPojo;

import java.util.List;

public interface IUserService {

    /**
     * 添加信息
     * @param userPojo
     * @return
     */
    public CommonResult addBean(UserPojo userPojo);

    /**
     * 通过id删除
     * @param id
     */
    public CommonResult deleteById(int id);

    /**
     * 批量删除
     * @param idStr
     * @return
     */
    public CommonResult deleteByChoiceId(String idStr);

    /**
     * 更新
     * @param userPojo
     */
    public CommonResult updateBean(UserPojo userPojo);

    /**
     * 查询满足条件的信息
     * @param searchVo
     * @param page
     * @return
     */
    public List<UserPojo> selectUserList(SearchVo searchVo, PageBean page);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public UserPojo getById(int id);


    /**
     * 通过手机号与密码获取信息
     * @param mobile ： 手机号
     * @param password ： 密码
     * @return
     */
    public UserPojo getByMobileAndPwd(String mobile, String password);

    /**
     * 注册
     * @param mobile
     * @param password
     * @param username
     * @param type
     * @return
     */
    public  CommonResult register(String mobile, String password, String username, int type);

    /**
     * 用于用户修改密码操作
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public CommonResult changePwd(int id, String oldPassword, String newPassword);

    /**
     * 忘记密码
     * @param username
     * @param mobile
     * @param newpassword
     * @return
     */
    public CommonResult forgetPwd(String username, String mobile, String newpassword);
}
