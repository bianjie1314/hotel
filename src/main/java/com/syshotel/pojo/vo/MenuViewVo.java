package com.syshotel.pojo.vo;

import com.syshotel.pojo.MenuPojo;

import java.io.Serializable;
import java.util.List;

/**
 * 前段菜单权限接收显示的格式
 */
public class MenuViewVo implements Serializable {

    //菜单编号
    private int parentId;
    //角色编号
    private int roleId;
    //菜单名称
    private String parentName;
    //图标
    private String parentIcon;
    //二级菜单
    private List<MenuPojo> items;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentIcon() {
        return parentIcon;
    }

    public void setParentIcon(String parentIcon) {
        this.parentIcon = parentIcon;
    }

    public List<MenuPojo> getItems() {
        return items;
    }

    public void setItems(List<MenuPojo> items) {
        this.items = items;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
