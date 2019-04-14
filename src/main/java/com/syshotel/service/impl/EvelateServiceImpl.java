package com.syshotel.service.impl;

import com.syshotel.common.CommonResult;
import com.syshotel.common.MessageConstant;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.dao.IEvelateDao;
import com.syshotel.pojo.EvelatePojo;
import com.syshotel.service.IEvelateService;
import com.syshotel.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EvelateServiceImpl implements IEvelateService {

    @Autowired
    IEvelateDao iEvelateDao;

    @Override
    public CommonResult addBean(EvelatePojo evelatePojo) {
        int result =  iEvelateDao.addBean(evelatePojo);
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
        iEvelateDao.deleteById(id);
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }

    @Override
    public CommonResult deleteByChoiceId(String idStr) {
        if (StringUtils.isEmpty(idStr)){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        String[] index = (idStr.substring(idStr.indexOf(',') + 1)).split(",");
        iEvelateDao.deleteByChoiceId( Arrays.asList(index));
        return CommonResult.SUCCESS(MessageConstant.DELETE_SUCCESS,null);
    }


    @Override
    public CommonResult updateBean(EvelatePojo evelatePojo) {
        if (evelatePojo == null || evelatePojo.getId() <= 0){
            return CommonResult.ERROR(MessageConstant.PARAM_ERROR);
        }
        iEvelateDao.updateBean(evelatePojo);
        return CommonResult.SUCCESS(MessageConstant.UPDATE_SUCCESS,null);
    }

    @Override
    public List<EvelatePojo> selectEvelateList(SearchVo searchVo,  PageBean page) {
        return iEvelateDao.selectEvelateList(getQueryMap(searchVo,page));
    }

    private Map<String,Object> getQueryMap(SearchVo searchVo,  PageBean page){
        Map<String,Object> paramMap = new HashMap<>();
        if (searchVo != null){
            if (!StringUtils.isEmpty(searchVo.getText())){
                paramMap.put("inputCheck","%"+searchVo.getText()+"%");
            }
            if (!StringUtils.isEmpty(searchVo.getCategory())){
                paramMap.put("status",searchVo.getCategory());
            }
            if (!StringUtils.isEmpty(searchVo.getStartTime())){
                paramMap.put("startTime",searchVo.getStartTime());
            }
            if (!StringUtils.isEmpty(searchVo.getEndTime())){
                paramMap.put("endTime",searchVo.getEndTime());
            }
            if (searchVo.getIndex() > 0){
                paramMap.put("roomId",searchVo.getIndex());
            }
            if (searchVo.getStatus() > 0){
                paramMap.put("status",searchVo.getStatus());
            }
            if (searchVo.getUserFlag() > 0){
                paramMap.put("userId",searchVo.getUserFlag());
            }
        }
        if (page != null && page.getStart() >= 0){
            paramMap.put("start",page.getStart());
            paramMap.put("offset",page.getOffset());
        }
        return paramMap;
    }


    @Override
    public CommonResult getEvelatePage(SearchVo searchVo, PageBean pageBean) {
        Map<String, Object> queryMap = getQueryMap(searchVo, pageBean);
        //数量为0
        int count = iEvelateDao.countEvelateList(queryMap);
        if (count == 0){
            return CommonResult.SUCCESS(0,null);
        }
        pageBean.setTotal(count);
        return CommonResult.SUCCESS(count,iEvelateDao.selectEvelateList(queryMap));
    }

    @Override
    public EvelatePojo getById(int id) {
        return iEvelateDao.getById(id);
    }
}
