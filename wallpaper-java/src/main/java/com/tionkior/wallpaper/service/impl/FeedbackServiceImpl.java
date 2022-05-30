package com.tionkior.wallpaper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tionkior.wallpaper.entity.Feedback;
import com.tionkior.wallpaper.entity.dto.FeedbackDTO;
import com.tionkior.wallpaper.entity.vo.Result;
import com.tionkior.wallpaper.entity.vo.ResultCodeEnum;
import com.tionkior.wallpaper.mapper.FeedbackMapper;
import com.tionkior.wallpaper.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author : TionKior
 * @date : 2022/5/25 20:46
 */

@Slf4j
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addFeedback(Map<String, Object> map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        // 因为是JSON数据,需要一个JSON中间转换
        String feedbackJSON = objectMapper.writeValueAsString(map.get("feedback"));
        String userIdJSON = objectMapper.writeValueAsString(map.get("userId"));
        Feedback feedback = objectMapper.readValue(feedbackJSON, Feedback.class);
        String userId = objectMapper.readValue(userIdJSON, String.class);

        if (ObjectUtils.isEmpty(feedback) || StringUtils.isEmpty(userId)) {
            return Result.failure(ResultCodeEnum.NULL);
        }
        feedback.setState(0);
        feedback.setFeedbackTime(new Timestamp(System.currentTimeMillis()));

        // 保存反馈信息
        boolean result = this.save(feedback);
        if (!result) {
            return Result.failure();
        }
        // 向反馈和用户中间表插入信息
        feedbackMapper.insertFeedbackAndUser(feedback.getId(), userId);
        return Result.success();

    }

    @Override
    public Result feedbackListStateIsZero() {
        List<FeedbackDTO> feedbackDTOList = feedbackMapper.getFeedbackListStateIsZero();

        return CollectionUtils.isEmpty(feedbackDTOList) ?
                Result.failure(ResultCodeEnum.NOT_HAVE_FEEDBACK) : Result.success(feedbackDTOList);
    }

    @Override
    public Result feedbackListStateIsOne() {
        List<FeedbackDTO> feedbackDTOList = feedbackMapper.getFeedbackListStateIsOne();

        return CollectionUtils.isEmpty(feedbackDTOList) ?
                Result.failure(ResultCodeEnum.NOT_HAVE_FEEDBACK) : Result.success(feedbackDTOList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updateFeedbackStateById(String feedbackId) {
        if (StringUtils.isEmpty(feedbackId)) {
            return Result.failure(ResultCodeEnum.NULL);
        }
        // 根据ID查询反馈信息
        Feedback feedback = this.getById(feedbackId);
        if (ObjectUtils.isEmpty(feedback)) {
            return Result.failure(ResultCodeEnum.NOT_HAVE_FEEDBACK);
        }
        // 更新反馈信息
        feedback.setState(1);
        boolean result = this.updateById(feedback);
        return result ? Result.success() : Result.failure();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result deleteFeedbackById(String feedbackId) {
        if (StringUtils.isEmpty(feedbackId)) {
            return Result.failure(ResultCodeEnum.NULL);
        }
        // 首先删除中间表信息
        feedbackMapper.deleteFeedbackAndUserByFeedbackId(feedbackId);
        // 删除反馈信息
        boolean result = this.removeById(feedbackId);
        return result ? Result.success() : Result.failure();
    }

    @Override
    public Result feedbackListByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return Result.failure(ResultCodeEnum.NULL);
        }
        List<FeedbackDTO> feedbackDTOList = feedbackMapper.getFeedbackListByUserId(userId);
        return CollectionUtils.isEmpty(feedbackDTOList) ?
                Result.failure(ResultCodeEnum.NOT_HAVE_FEEDBACK) : Result.success(feedbackDTOList);
    }

}
