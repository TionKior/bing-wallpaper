package com.tionkior.wallpaper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tionkior.wallpaper.entity.Category;
import com.tionkior.wallpaper.entity.Feedback;
import com.tionkior.wallpaper.entity.vo.Result;

import java.util.Map;

/**
 * @author : TionKior
 * @date : 2022/5/25 20:45
 */

public interface FeedbackService extends IService<Feedback> {

    /**
     * 新增用户反馈
     *
     * @param map 参数
     * @return 返回结果
     */
    Result addFeedback(Map<String, Object> map) throws JsonProcessingException;

    /**
     * 未解决的用户反馈列表
     *
     * @return 返回结果
     */
    Result feedbackListStateIsZero();

    /**
     * 查询所有已解决反馈信息
     *
     * @return
     */
    Result feedbackListStateIsOne();

    /**
     * 根据ID更新反馈信息状态
     *
     * @param feedbackId 返回ID
     * @return 返回结果
     */
    Result updateFeedbackStateById(String feedbackId);

    /**
     * 根据反馈ID删除反馈信息
     *
     * @param feedbackId 反馈ID
     * @return 返回结果
     */
    Result deleteFeedbackById(String feedbackId);

    /**
     * 根据用户ID查询反馈信息
     *
     * @param userId 用户ID
     * @return 返回结果
     */
    Result feedbackListByUserId(String userId);
}
