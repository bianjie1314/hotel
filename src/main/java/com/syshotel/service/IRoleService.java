package com.syshotel.service;

import com.syshotel.common.CommonResult;
import com.syshotel.common.PageBean;
import com.syshotel.common.SearchVo;
import com.syshotel.pojo.RolePojo;

import java.util.List;

public interface IRoleService {

    /**
     * 添加信息
     * @param rolePojo
     * @return
     */
    public CommonResult addBean(RolePojo rolePojo);

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
         * 查询满足条件的信息
         * @param searchVo
         * @param page
         * @return
         */
    public List<RolePojo> selectRoleList(SearchVo searchVo, PageBean page);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public RolePojo getById(int id);

}
