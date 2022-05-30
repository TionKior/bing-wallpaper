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
 * @author : TionKior
 * @date : 2022/2/25 8:55
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("分类实体类")
public class Category implements Serializable {

    @ApiModelProperty(value = "主键id", example = "0")
    @TableId
    private Long id;
    @ApiModelProperty("分类类型")
    private String category;

}
