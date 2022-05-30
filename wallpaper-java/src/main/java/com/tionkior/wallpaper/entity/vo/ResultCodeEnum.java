package com.tionkior.wallpaper.entity.vo;

/**
 * 返回结果枚举类
 *
 * @author TionKior
 */
public enum ResultCodeEnum {
    SUCCESS("0000", "操作成功"),
    FAIL("0001", "操作失败"),
    NOREPEAT("0004", "参数不可重复"),

    NOT_HAVE_WALLPAPER("0002", "没有壁纸"),
    NULL("0005", "参数为空"),
    FAVORITE_IMAGE_EXIST("0006", "该图片已被收藏"),
    NOT_FAVORITE_IMAGE("0007", "该图片还没有被收藏"),
    NOT_USER("0008", "用户不存在"),
    NOT_HAVE_FEEDBACK("0009", "没有反馈信息"),
    NOT_LOGIN("0010", "用户未登录"),
    USER_EXIST("0011", "用户已存在");
    String code;
    String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}