package com.tionkior.wallpaper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户表(User)实体类
 *
 * @author makejava
 * @since 2022-03-12 14:57:02
 */
@ApiModel("用户实体类")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 850639118366352627L;
    /**
     * 主键,用户ID
     */
    @TableId
    @ApiModelProperty(value = "主键id", example = "0")
    private Integer userId;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String userPassword;
    /**
     * 用户盐
     */
    @ApiModelProperty("用户盐")
    private String userSalt;
    /**
     * 用户积分
     */
    private Integer userIntegral;
    /**
     * 账户是否被锁定,1:锁定,0:未锁定
     */
    @ApiModelProperty("账户是否被锁定,1:锁定,0:未锁定")
    private String userLocked;

}

