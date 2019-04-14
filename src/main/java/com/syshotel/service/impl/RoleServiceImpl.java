package com.syshotel.service.impl;

import com.syshotel.common.*;
import com.syshotel.dao.IRoleDao;
import com.syshotel.pojo.RolePojo;
import com.syshotel.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    IRoleDao iRoleDao;

    @Override
    public CommonResult addBean(RolePojo rolePojo) {
        if (rolePojo == null || StringUtils.isEmpty(rolePojo.getName())){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        int num = iRoleDao.countByName(rolePojo.getName());
        if (num > 0){
            return CommonResult.ERROR(MessageConstant.ROLE_EXISTS);
        }

        rolePojo.setCreateTime(new Date());
        int result = iRoleDao.addBean(rolePojo);
        if (result <= 0){
            return CommonResult.ERROR(MessageConstant.ADD_FAILURE);
        }
        return CommonResult.SUCCESS(MessageConstant.ADD_SUCCESS,result);
    }

    @Override
    public CommonResult deleteByChoiceId(String idStr) {
        if (StringUtils.isEmpty(idStr)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        String[] index = (idStr.substring(idStr.indexOf(',') + 1)).split(",");
        iRoleDao.deleteByChoiceId( Arrays.asList(index));

        //TODO 删除角色关联的信息

        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    @Override
    public CommonResult deleteById(int id) {
        if (id <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        iRoleDao.deleteById(id);

        //TODO 删除角色关联的信息


        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    @Override
    public List<RolePojo> selectRoleList(SearchVo searchVo, PageBean page) {
        Map<String,Object> paramMap = new HashMap<>();
        if (searchVo != null){
            if (!StringUtils.isEmpty(searchVo.getText())){
                paramMap.put("name",searchVo.getText());
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
        return iRoleDao.selectRoleList(paramMap);
    }


    @Override
    public RolePojo getById(int id) {
        return iRoleDao.getById(id);
    }
}
