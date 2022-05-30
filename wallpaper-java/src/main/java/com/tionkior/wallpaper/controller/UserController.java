package com.tionkior.wallpaper.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tionkior.wallpaper.entity.User;
import com.tionkior.wallpaper.entity.vo.Result;
import com.tionkior.wallpaper.entity.vo.ResultCodeEnum;
import com.tionkior.wallpaper.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : TionKior
 * @date : 2022/3/12 17:34
 */

@Slf4j
@RestController
@Api(tags = "用户Controller")
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result userLogin(@ApiParam("登录用户") @RequestBody User user) {

        log.info("用户登陆请求到达");
        // 登录失败从request中获取shiro处理的异常信息
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getUserPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            // 从UserRealm里返回的SimpleAuthenticationInfo获取到认证成功的用户名，
            // subject.getPrincipal()获取的是SimpleAuthenticationInfo设置的第一个参数
            User loginUser = (User) subject.getPrincipal();
            Session session = subject.getSession();
            session.setAttribute("loginUser", loginUser);
            // SessionCache.sessionCache.put((String)subject.getSession().getId(),session);
            loginUser.setUserPassword("");

            // 返回成功信息
            Map<String, Object> map = new HashMap<>(10);
            map.put("loginUser", loginUser);
            map.put("token", subject.getSession().getId());

            return Result.success(map);
        } catch (IncorrectCredentialsException e) {
            return Result.failure("0001", "密码错误");
        } catch (LockedAccountException e) {
            return Result.failure("0002", "账户被冻结");
        } catch (AuthenticationException e) {
            return Result.failure("0003", "账户不存在");
        } catch (Exception e) {
            // Redis有问题,总是走的这里
            e.printStackTrace();
        }
        User userForData = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, user.getUserName()));
        return Result.success(userForData);
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result userRegister(@ApiParam("注册用户") @RequestBody User user) {
        return userService.register(user);
    }

    @ApiOperation("登出")
    @GetMapping("/logout")
    public Result userLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success();
    }

    @ApiOperation("用户列表")
    @GetMapping("/userList")
    public Result userList() {
        return userService.getUserList();
    }

    @ApiOperation("通过用户ID删除用户")
    @GetMapping("/deleteUserById")
    public Result deleteUserById(@ApiParam("被删除的用户ID") String userId) {
        log.error("userId:{}", userId);
        return userService.deleteUserById(userId);
    }

    @ApiOperation("根据用户ID获取用户信息")
    @GetMapping("/findUserById")
    public Result findUserById(@ApiParam("被删除的用户ID") String userId) {
        if (StringUtils.isEmpty(userId)) {
            return Result.failure(ResultCodeEnum.NULL);
        }
        User user = userService.getById(userId);
        if (ObjectUtils.isEmpty(user)) {
            return Result.failure(ResultCodeEnum.NOT_USER);
        }
        return Result.success(user);
    }

    @PostMapping("/lockUser")
    public Result lockUser(@ApiParam("被锁定的用户ID") @RequestBody String userId) {
        return userService.lockUser(userId);
    }

    @PostMapping("/unLockUser")
    public Result unLockUser(@ApiParam("需要解锁的用户ID") @RequestBody String userId) {
        return userService.unLockUser(userId);
    }

}
