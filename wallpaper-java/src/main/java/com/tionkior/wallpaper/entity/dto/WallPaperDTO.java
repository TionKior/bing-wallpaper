package com.tionkior.wallpaper.entity.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author : TionKior
 * @date : 2022/5/24 11:30
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WallPaperDTO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键id", example = "0")
    @TableId
    private Long id;
    /**
     * 图片url
     */
    @ApiModelProperty("图片url")
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
    @ApiModelProperty("时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    /**
     * 分类(不是ID，是中文名称)
     */
    @ApiModelProperty("分类")
    private String category;

}
