package com.tionkior.wallpaper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tionkior.wallpaper.entity.Feedback;
import com.tionkior.wallpaper.entity.vo.Result;
import com.tionkior.wallpaper.service.FeedbackService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : TionKior
 * @date : 2022/5/25 20:39
 */

@Slf4j
@RestController
@RequestMapping("/feedback")
@Api(tags = "反馈信息Controller")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/add")
    public Result addFeedback(@RequestBody Map<String, Object> map) throws JsonProcessingException {
        return feedbackService.addFeedback(map);
    }

    @GetMapping("/feedbackListStateIsZero")
    public Result feedbackListStateIsZero() {
        return feedbackService.feedbackListStateIsZero();
    }

    @GetMapping("/feedbackListStateIsOne")
    public Result feedbackListStateIsOne() {
        return feedbackService.feedbackListStateIsOne();
    }

    @PostMapping("/updateFeedbackStateById")
    public Result updateFeedbackStateById(@RequestBody String feedbackId) {
        return feedbackService.updateFeedbackStateById(feedbackId);
    }

    @GetMapping("/deleteById")
    public Result deleteFeedbackById(String feedbackId) {
        return feedbackService.deleteFeedbackById(feedbackId);
    }

    @PostMapping("/feedbackListByUserId")
    public Result feedbackListByUserId(@RequestBody String userId) {
        // 用户查看自己的反馈信息
        return feedbackService.feedbackListByUserId(userId);
    }


}
