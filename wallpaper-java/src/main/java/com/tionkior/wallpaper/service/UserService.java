package com.tionkior.wallpaper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tionkior.wallpaper.entity.User;
import com.tionkior.wallpaper.entity.vo.Result;

/**
 * @author : TionKior
 * @date : 2022/5/23 20:05
 */

public interface UserService extends IService<User> {


    /**
     * 用户注册
     *
     * @param user 用户账号和密码
     * @return 返回结果
     */
    Result register(User user);


    /**
     * 返回用户列表
     *
     * @return 返回结果
     */
    Result getUserList();

    /**
     * 根据用户ID删除用户
     *
     * @param userId 用户ID
     * @return 返回结果
     */
    Result deleteUserById(String userId);

    /**
     * 锁定用户
     *
     * @param userId 用户ID
     * @return 返回结果
     */
    Result lockUser(String userId);

    /**
     * 解锁用户
     *
     * @param userId 用户ID
     * @return 返回结果
     */
    Result unLockUser(String userId);

    /**
     * 用户添加积分
     *
     * @param userId                用户ID
     * @param addPointsForOneUpload 积分数量
     */
    void addUserIntegral(String userId, Integer addPointsForOneUpload);


}
