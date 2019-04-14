package com.syshotel.service;

import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.EvelatePojo;

import java.util.List;

public interface IEvelateService {

    /**
     * 添加信息
     * @param evelatePojo
     * @return
     */
    public CommonResult addBean(EvelatePojo evelatePojo);

    /**
     * 通过id删除
     * @param id
     */
    public CommonResult deleteById(int id);

    /**
     * 更新
     * @param evelatePojo
     */
    public CommonResult updateBean(EvelatePojo evelatePojo);

    /**
     * 查询满足条件的信息
     * @param searchVo
     * @param page
     * @return
     */
    public List<EvelatePojo> selectEvelateList(SearchVo searchVo,  PageBean page);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public EvelatePojo getById(int id);

    /**
     * 批量删除
     * @param choiceId
     * @return
     */
    public CommonResult deleteByChoiceId(String choiceId);

    /**
     * 获取
     * @param searchVo
     * @param pageBean
     * @return
     */
    public CommonResult getEvelatePage(SearchVo searchVo, PageBean pageBean);
}
