package com.tionkior.wallpaper.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : TionKior
 * @date : 2022/5/26 11:01
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FeedbackDTO implements Serializable {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 反馈人姓名
     */
    private String userName;

    /**
     * 反馈标题
     */
    private String title;

    /**
     * 反馈正文
     */
    private String content;

    /**
     * 反馈时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String feedbackTime;

    /**
     * 是否已解决
     */
    private String state;

}
