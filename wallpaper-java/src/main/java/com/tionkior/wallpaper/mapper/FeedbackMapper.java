package com.tionkior.wallpaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tionkior.wallpaper.entity.Feedback;
import com.tionkior.wallpaper.entity.dto.FeedbackDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : TionKior
 * @date : 2022/5/25 20:45
 */

@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {

    /**
     * 向反馈和用户中间表插入信息
     *
     * @param id     反馈ID
     * @param userId 用户ID
     */
    @Insert("insert into user_feedback(feedback_id,user_id) values(#{id},#{userId})")
    void insertFeedbackAndUser(Integer id, String userId);

    /**
     * 查询未解决反馈列表
     *
     * @return 返回结果
     */
    @Select("select feedback.id, user_name, title, content, feedback_time, state\n" +
            "from feedback\n" +
            "         left join user_feedback uf on feedback.id = uf.feedback_id\n" +
            "         left join user u on uf.user_id = u.user_id\n" +
            "where state = 0")
    List<FeedbackDTO> getFeedbackListStateIsZero();

    /**
     * 查询已解决反馈列表
     *
     * @return 返回结果
     */
    @Select("select feedback.id, user_name, title, content, feedback_time, state\n" +
            "from feedback\n" +
            "         left join user_feedback uf on feedback.id = uf.feedback_id\n" +
            "         left join user u on uf.user_id = u.user_id\n" +
            "where state = 1")
    List<FeedbackDTO> getFeedbackListStateIsOne();

    /**
     * 删除反馈信息
     *
     * @return 返回结果
     */
    @Delete("delete from user_feedback where feedback_id = #{feedbackId}")
    void deleteFeedbackAndUserByFeedbackId(String feedbackId);

    /**
     * 根据用户ID查询反馈信息
     *
     * @param userId 用户ID
     * @return 返回结果
     */
    @Select("select feedback.id, user_name, title, content, feedback_time, state\n" +
            "from feedback\n" +
            "         left join user_feedback uf on feedback.id = uf.feedback_id\n" +
            "         left join user u on uf.user_id = u.user_id\n" +
            "where uf.user_id = #{userId}")
    List<FeedbackDTO> getFeedbackListByUserId(String userId);
}
