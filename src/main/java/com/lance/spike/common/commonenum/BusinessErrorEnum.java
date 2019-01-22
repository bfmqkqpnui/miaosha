package com.lance.spike.common.commonenum;

import com.lance.spike.error.ICommonError;

/**
 * @ClassName CommonErrorEnum
 * @Description TODO
 * @Author 陆逸飞
 * @Date 2018-12-19 16:36
 * @Version v1.0
 */
public enum BusinessErrorEnum implements ICommonError {
    // 10000开头 系统通用错误
    DEFAULT_ERROR("100005", "系统错误，请稍后再试。"),
    UNKNOW_ERROR("100001", "未知错误"),
    // 20000开头 为用户信息相关错误
    USER_NOT_EXIST("200001", "用户不存在"),
    NOT_PHONENUM("200002", "手机号码不正确"),
    UNEQUAL_SMS("200003", "短信验证码不正确"),
    UNEQUAL_VERIFYCODE("200004", "校验码不正确"),
    VERIFYCODE_NOT_EXIST("200005", "校验码必填"),
    NAME_NOT_EXIST("200006", "登录账号必填"),
    PASSWORD_NOT_EXIST("200007", "登录密码必填"),;

    private BusinessErrorEnum(String resCode, String msg) {
        this.resCode = resCode;
        this.msg = msg;
    }

    private String resCode;
    private String msg;

    @Override
    public String getResCode() {
        return this.resCode;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public ICommonError setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
