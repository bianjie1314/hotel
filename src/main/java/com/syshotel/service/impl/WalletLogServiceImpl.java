package com.syshotel.service.impl;

import com.syshotel.common.CommonResult;
import com.syshotel.common.MessageConstant;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.dao.IWalletLogDao;
import com.syshotel.pojo.WalletLogPojo;
import com.syshotel.service.IWalletLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WalletLogServiceImpl implements IWalletLogService {

    @Autowired
    IWalletLogDao iWalletLogDao;

    @Override
    public CommonResult addBean(WalletLogPojo walletLogPojo) {
        int result = iWalletLogDao.addBean(walletLogPojo);
        if (result <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        return CommonResult.SUCCESS(MessageConstant.ADD_SUCCESS,result);
    }

    @Override
    public CommonResult deleteById(int id) {
        if (id <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        iWalletLogDao.deleteById(id);
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    @Override
    public CommonResult deleteByChoiceId(String idStr) {
        if (StringUtils.isEmpty(idStr)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        String[] index = (idStr.substring(idStr.indexOf(',') + 1)).split(",");
        iWalletLogDao.deleteByChoiceId( Arrays.asList(index));
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    @Override
    public List<WalletLogPojo> selectWalletList(SearchVo searchVo, PageBean page) {
        Map<String,Object> paramMap = new HashMap<>();
        if (searchVo != null){
            if (!StringUtils.isEmpty(searchVo.getText())){
                paramMap.put("inputCheck","%"+searchVo.getText()+"%");
            }
            if (!StringUtils.isEmpty(searchVo.getCategory())){
                paramMap.put("type",searchVo.getCategory());
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
        return iWalletLogDao.selectWalletLogList(paramMap);
    }

    @Override
    public WalletLogPojo getById(int id) {
        return iWalletLogDao.getById(id);
    }

}
