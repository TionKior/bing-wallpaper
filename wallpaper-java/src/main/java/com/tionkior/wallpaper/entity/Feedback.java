package com.tionkior.wallpaper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * 用户反馈表(Feedback)实体类
 *
 * @author makejava
 * @since 2022-05-25 20:39:43
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("反馈实体表")
public class Feedback implements Serializable {
    private static final long serialVersionUID = -30857981679677109L;

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    @TableId
    private Integer id;
    /**
     * 反馈标题
     */
    @ApiModelProperty("反馈标题")
    private String title;
    /**
     * 反馈正文
     */
    @ApiModelProperty("反馈正文")
    private String content;
    /**
     * 反馈时间
     */
    @ApiModelProperty("反馈时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp feedbackTime;

    /**
     * 是否解决(0:未解决,1:已解决)
     */
    @ApiModelProperty("是否解决(0:未解决,1:已解决)")
    private Integer state;

}

