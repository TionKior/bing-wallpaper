package com.tionkior.wallpaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tionkior.wallpaper.entity.User;
import com.tionkior.wallpaper.entity.WallPaper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author : TionKior
 * @date : 2022/3/12 16:39
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where user_name = #{userName}")
    User getOneUserInfo(String userName);
}
