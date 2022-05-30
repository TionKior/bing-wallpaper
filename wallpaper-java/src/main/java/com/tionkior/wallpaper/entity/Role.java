package com.tionkior.wallpaper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色表(Role)实体类
 *
 * @author makejava
 * @since 2022-03-12 16:39:20
 */
@ApiModel("角色实体类")
@ToString
public class Role implements Serializable {
    private static final long serialVersionUID = -43144697247221461L;
    /**
     * 主键,角色ID
     */
    @ApiModelProperty(value = "主键id", example = "0")
    @TableId
    private Integer roleId;
    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String roleName;
    /**
     * 是否可用,1:可用,0:不可用
     */
    @ApiModelProperty("是否可用,1:可用,0:不可用")
    private String roleAvailable;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleAvailable() {
        return roleAvailable;
    }

    public void setRoleAvailable(String roleAvailable) {
        this.roleAvailable = roleAvailable;
    }

}

