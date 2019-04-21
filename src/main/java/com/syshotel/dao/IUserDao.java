package com.syshotel.dao;

import com.syshotel.pojo.UserPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserDao {

    /**
     * 添加信息
     * @param userPojo
     * @return
     */
    public int addBean(UserPojo userPojo);

    /**
     * 通过id删除
     * @param id
     */
    public void deleteById(int id);

    /**
     * 更新
     * @param userPojo
     */
    public void updateBean(UserPojo userPojo);

    /**
     * 查询满足条件的信息
     * @param paramMap
     * @return
     */
    public List<UserPojo> selectUserList(Map<String, Object> paramMap);

    /**
     * 获取手机号对应的信息
     * @param mobile
     * @return
     */
    public List<UserPojo> getByMobile(@Param("mobile") String mobile);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public UserPojo getById(int id);


    /**
     * 获取所有字段信息
     * @param id
     * @return
     */
    public UserPojo getAllParamById(int id);


    /**
     * 通过手机号与密码获取信息
     * @param mobile ： 手机号
     * @param password ： 密码
     * @return
     */
    public UserPojo getByMobileAndPwd(@Param("mobile") String mobile, @Param("password") String password);


    /**
     * 通过id集合遍历删除
     * @param ids
     */
    public void deleteByChoiceId(@Param("ids") List ids);

    /**
     * 通过手机号查询
     * @param mobile
     * @return
     */
    public int countByMobile(@Param("mobile") String mobile);

    /**
     * 通过用户名和手机号进行获取用户信息
     * @param username
     * @param mobile
     * @return
     */
    public UserPojo getByMobileAndUsername(@Param("username") String username, @Param("mobile") String mobile);

    /**
     * 更新用户账户余额
     * @param user
     */
    public void updateMoney(UserPojo user);
}
