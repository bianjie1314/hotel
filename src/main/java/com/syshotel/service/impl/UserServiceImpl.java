package com.syshotel.service.impl;

import com.syshotel.common.*;
import com.syshotel.dao.IUserDao;
import com.syshotel.dao.IWalletLogDao;
import com.syshotel.pojo.UserPojo;
import com.syshotel.pojo.WalletLogPojo;
import com.syshotel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDao iUserDao;
    @Autowired
    IWalletLogDao iWalletLogDao;

    @Override
    public CommonResult addBean(UserPojo userPojo) {
        if (userPojo == null || StringUtils.isEmpty(userPojo.getMobile())){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        int num = iUserDao.countByMobile(userPojo.getMobile());
        if (num > 0){
            return CommonResult.ERROR(MessageConstant.MOBILE_EXISTS);
        }

        userPojo.setCreateTime(new Date());
        int result = iUserDao.addBean(userPojo);
        if (result <= 0){
            return CommonResult.ERROR(MessageConstant.RUN_ERROR);
        }
        return CommonResult.SUCCESS(MessageConstant.ADD_SUCCESS,result);
    }

    @Override
    public CommonResult deleteById(int id) {
        if (id <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        iUserDao.deleteById(id);
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    @Override
    public CommonResult deleteByChoiceId(String idStr) {
        if (StringUtils.isEmpty(idStr)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        String[] index = (idStr.substring(idStr.indexOf(',') + 1)).split(",");
        iUserDao.deleteByChoiceId( Arrays.asList(index));
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    @Override
    public CommonResult updateBean(UserPojo userPojo) {
        if (userPojo == null || userPojo.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        userPojo.setUpdateTime(new Date());
        iUserDao.updateBean(userPojo);
        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,iUserDao.getById(userPojo.getId()));
    }

    @Override
    public List<UserPojo> selectUserList(SearchVo searchVo, PageBean page) {
        Map<String,Object> paramMap = new HashMap<>();
        if (searchVo != null){
            if (!StringUtils.isEmpty(searchVo.getText())){
                paramMap.put("inputCheck","%"+searchVo.getText()+"%");
            }
            if (!StringUtils.isEmpty(searchVo.getStartTime())){
                paramMap.put("startTime",searchVo.getStartTime());
            }
            if (!StringUtils.isEmpty(searchVo.getEndTime())){
                paramMap.put("endTime",searchVo.getEndTime());
            }
        }
        if (page != null && page.getStart() >= 0){
            paramMap.put("start",page.getStart());
            paramMap.put("offset",page.getOffset());
        }
        return iUserDao.selectUserList(paramMap);
    }

    @Override
    public UserPojo getById(int id) {
        return iUserDao.getById(id);
    }

    @Override
    public UserPojo getByMobileAndPwd(String mobile, String password) {
        return iUserDao.getByMobileAndPwd(mobile,password);
    }

    @Override
    public CommonResult register(String mobile, String password,String username,int type) {
        //判断输入的参数是否存在空值
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) ||StringUtils.isEmpty(username) || StringUtils.isEmpty(type)){
            return  CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        int count = iUserDao.countByMobile(mobile);
        if (count > 0){//已经注册过
            return  CommonResult.ERROR(MessageConstant.MOBILE_HAS_REG);
        }
        UserPojo userPojo = new UserPojo();
        userPojo.setCreateTime(new Date());
        if (type ==Constant.USER_ADMIN){//管理员
            userPojo.setRoleId(Constant.USER_ADMIN);
        }else if (type ==Constant.USER_SHOP){//商户
            userPojo.setRoleId(Constant.USER_SHOP);
        }else {//普通用户
            userPojo.setRoleId(Constant.USER_COMMON);
        }
        userPojo.setPassword(password);
        userPojo.setUsername(username);
        userPojo.setMobile(mobile);
        iUserDao.addBean(userPojo);
        return CommonResult.SUCCESS(userPojo);
    }

    @Override
    public CommonResult changePwd(int id, String oldPassword, String newPassword) {
        //判断输入的参数是否存在空值
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        UserPojo us = iUserDao.getAllParamById(id);
        if (us == null){
            return  CommonResult.ERROR(MessageConstant.USER_NO_EXISTS);
        }
        if (!us.getPassword().equals(oldPassword)){//密码正常
            return  CommonResult.ERROR(MessageConstant.OLD_PWD_ERROR);
        }
        UserPojo userPojo = new UserPojo();
        userPojo.setUpdateTime(new Date());
        userPojo.setId(id);
        userPojo.setPassword(newPassword);
        iUserDao.updateBean(userPojo);
        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS);
    }

    @Override
    public CommonResult forgetPwd(String username, String mobile, String newpassword) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(newpassword)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        UserPojo us = iUserDao.getByMobileAndUsername(username,mobile);
        if (us == null){
            return CommonResult.ERROR(MessageConstant.USER_CHECK_ERROR);
        }
        UserPojo updateUs = new UserPojo();
        updateUs.setId(us.getId());
        updateUs.setPassword(newpassword);
        iUserDao.updateBean(updateUs);
        return CommonResult.SUCCESS(MessageConstant.MODIFY_SUCCESS);
    }

    @Override
    public CommonResult batchUpdateMoney(List<UserPojo> userPojos) {
        if (userPojos == null || userPojos.size() == 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        for (UserPojo user:userPojos) {
            iUserDao.updateMoney(user);
        }

        return CommonResult.SUCCESS(MessageConstant.MODIFY_SUCCESS);
    }

    @Override
    public CommonResult updateMoney(UserPojo userPojos) {
        if (userPojos == null || userPojos.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        iUserDao.updateMoney(userPojos);
        return CommonResult.SUCCESS(MessageConstant.MODIFY_SUCCESS);
    }

    @Transactional
    @Override
    public CommonResult addBalance(double money, UserPojo userInfo) {
        if (userInfo == null || userInfo.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        UserPojo us = new UserPojo();
        us.setBalance(money);
        us.setId(userInfo.getId());
        iUserDao.updateMoney(us);


        WalletLogPojo walletLogPojo = new WalletLogPojo();
        walletLogPojo.setType(Constant.WALLET_ADD_1);
        walletLogPojo.setMoney(money);
        walletLogPojo.setCreateTime(new Date());
        walletLogPojo.setUserId(userInfo.getId());
        walletLogPojo.setNote("账户充值");
        iWalletLogDao.addBean(walletLogPojo);

        return CommonResult.SUCCESS(MessageConstant.MODIFY_SUCCESS);
    }
}
