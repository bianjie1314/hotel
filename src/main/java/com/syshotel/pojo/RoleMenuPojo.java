package com.syshotel.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色对应的菜单权限
 */
public class RoleMenuPojo implements Serializable {

    private int id;
    //角色编号
    private int roleId;
    //菜单编号
    private int menuId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}