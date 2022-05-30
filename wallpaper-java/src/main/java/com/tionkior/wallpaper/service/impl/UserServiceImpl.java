package com.tionkior.wallpaper.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tionkior.wallpaper.entity.User;
import com.tionkior.wallpaper.entity.vo.Result;
import com.tionkior.wallpaper.entity.vo.ResultCodeEnum;
import com.tionkior.wallpaper.mapper.UserMapper;
import com.tionkior.wallpaper.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : TionKior
 * @date : 2022/5/23 20:06
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public Result register(User user) {
        if (ObjectUtils.isEmpty(user)) {
            return Result.failure(ResultCodeEnum.NULL);
        }

        // 查询用户是否存在
        User userByData = this.getOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, user.getUserName()));
        if (ObjectUtils.isNotEmpty(userByData)) {
            return Result.failure(ResultCodeEnum.USER_EXIST);
        }

        // 加密方式   MD5盐 盐值:ByteSource.Util.bytes(UserName + salt) 二次散列 Hex密码加密编码
        System.out.println(new Md5Hash("123", ByteSource.Util.bytes("ssxsalt"), 2).toHex());

        String salt = user.getUserName() + "salt";
        // 加密用户密码
        String userSalePassword = new Md5Hash(user.getUserPassword(), ByteSource.Util.bytes(user.getUserName() + "salt"), 2).toHex();
        // 设置用户密码
        user.setUserPassword(userSalePassword);
        // 设置用户相关信息
        user.setUserLocked("0");
        user.setUserSalt(salt);
        boolean result = this.save(user);
        return result ? Result.success() : Result.failure();
    }

    @Override
    public Result getUserList() {
        List<User> userList = this.list();
        return CollectionUtils.isEmpty(userList) ? Result.failure() : Result.success(userList);
    }

    @Override
    public Result deleteUserById(String userId) {
        boolean result = this.removeById(userId);
        return result ? Result.success() : Result.failure();
    }

    @Override
    public Result lockUser(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return Result.failure(ResultCodeEnum.NULL);
        }
        User user = this.getById(userId);
        if (ObjectUtils.isEmpty(user)) {
            return Result.failure(ResultCodeEnum.NOT_USER);
        }
        user.setUserLocked("1");
        boolean result = this.updateById(user);

        return result ? Result.success() : Result.failure();
    }

    @Override
    public Result unLockUser(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return Result.failure(ResultCodeEnum.NULL);
        }
        User user = this.getById(userId);
        if (ObjectUtils.isEmpty(user)) {
            return Result.failure(ResultCodeEnum.NOT_USER);
        }
        user.setUserLocked("0");
        boolean result = this.updateById(user);

        return result ? Result.success() : Result.failure();
    }

    @Override
    public void addUserIntegral(String userId, Integer addPointsForOneUpload) {
        User user = this.getById(userId);
        if (ObjectUtils.isEmpty(user)) {
            return;
        }
        user.setUserIntegral(user.getUserIntegral() + addPointsForOneUpload);
        this.updateById(user);
    }

}
