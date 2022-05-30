package com.tionkior.wallpaper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author : TionKior
 * @date : 2022/2/24 12:02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("wallpaper")
@ToString
@ApiModel("壁纸实体类")
public class WallPaper implements Serializable {

    /**
     * 主键ID
     */
    @TableId
    @ApiModelProperty(value = "主键id", example = "0")
    private Long id;
    /**
     * 图片url
     */
    @ApiModelProperty("图片URL")
    private String imageUrl;
    /**
     * 图片名称
     */
    @ApiModelProperty("图片名称")
    private String imageName;
    /**
     * 作者
     */
    @ApiModelProperty("作者")
    private String author;
    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;
    /**
     * 时间
     */
    @ApiModelProperty("拍摄时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp createTime;
    /**
     * 分类ID
     */
    @ApiModelProperty("分类id")
    private String categoryId;

}
